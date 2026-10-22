# Secure Banking System - Security Guide

## 1. How to run

1. Check the database settings in `src/main/resources/application.properties` (Oracle, as already configured in this project).
2. Start the app. `DataInitializer` creates the roles, permissions and demo users the first time.
3. Import `docs/SecureBanking.postman_collection.json` into Postman.
4. Run the tests (no Oracle needed, they use H2): `./mvnw test`

Demo users (password for all: `Password@123`)

| Email | Role(s) | Customer id |
|---|---|---|
| admin@bank.com | ADMIN | 1 |
| manager@bank.com | BANK_MANAGER | 2 |
| auditor@bank.com | AUDITOR | 3 |
| eslam@bank.com | BANK_MANAGER + AUDITOR | 4 |
| ahmed@bank.com | CUSTOMER (account 1) | 5 |
| sara@bank.com | CUSTOMER (account 2) | 6 |

Login method: **HTTP Basic** (email + password in the `Authorization` header, sent on every request).
Because nothing is stored in a session, a blocked or disabled customer is rejected on the very next request.
That is our answer to "what happens to existing logins when a customer is blocked".
Call `GET /auth/me` to check a login.

## 2. Roles and permissions

| Permission | ADMIN | BANK_MANAGER | CUSTOMER | AUDITOR |
|---|:-:|:-:|:-:|:-:|
| ACCOUNT_READ | yes | yes | yes | yes |
| ACCOUNT_CREATE | yes | | | |
| ACCOUNT_UPDATE | yes | yes | | |
| ACCOUNT_DELETE | yes | | | |
| TRANSFER_CREATE | yes | | yes | |
| TRANSFER_READ | yes | yes | yes | yes |
| TRANSFER_APPROVE | yes | yes | | |
| CUSTOMER_READ | yes | yes | | |
| CUSTOMER_UPDATE | yes | yes | | |
| REPORT_VIEW | yes | | | yes |

Note: Task 5 does not list `TRANSFER_CREATE` for ADMIN, but Task 26 ("ADMIN has all permissions") and the matrix in Task 38 say ADMIN can create transfers, so ADMIN has it.

### Authorities
A role becomes `ROLE_<NAME>` and every permission of that role is added as it is.
ADMIN gets: `ROLE_ADMIN, ACCOUNT_READ, ACCOUNT_CREATE, ... REPORT_VIEW`.

## 3. Security rules matrix

| Operation | ADMIN | BANK_MANAGER | CUSTOMER | AUDITOR | Enforced by |
|---|:-:|:-:|:-:|:-:|---|
| Create Customer | yes | no | no | no | `POST /admin/customers` (role ADMIN) |
| Read Customer | yes | yes | no | no | `CUSTOMER_READ` |
| Update Customer | yes | yes | no | no | `CUSTOMER_UPDATE` |
| Create Account | yes | no | no | no | `ACCOUNT_CREATE` |
| Read Account | yes | yes | own only | yes | `ACCOUNT_READ` + ownership check |
| Update Account | yes | yes | no | no | `ACCOUNT_UPDATE` |
| Delete Account | yes | no | no | no | `ACCOUNT_DELETE` |
| Create Transfer | yes | no | yes | no | `TRANSFER_CREATE` + source account ownership |
| Read Transfer | yes | yes | own only | yes | `TRANSFER_READ` + ownership check |
| Approve Transfer | yes | yes | no | no | `TRANSFER_APPROVE` |
| View Reports | yes | no | no | yes | `REPORT_VIEW` |

## 4. Role vs Permission - when to use which

| | Role (`hasRole('ADMIN')`) | Permission (`hasAuthority('TRANSFER_APPROVE')`) |
|---|---|---|
| Meaning | WHO you are | WHAT you may do |
| Use for | Big areas of the app | A single action |
| Example here | `/admin/**` is ADMIN only | `POST /transfers/{id}/approve` needs `TRANSFER_APPROVE` |
| Good because | Short and easy to read | Roles can change without touching the code |

Rule of thumb: **roles for high-level access (whole areas), permissions for fine-grained access (single operations).**
If tomorrow the AUDITOR must approve transfers, we only add `TRANSFER_APPROVE` to that role in the database. No code changes.

Ownership ("own only") is a third check that neither roles nor permissions can express alone.
The service compares the owner of the data with the id of the logged-in user from the `SecurityContext`.

## 5. Where each security piece lives

| Piece | Class |
|---|---|
| PasswordEncoder (BCrypt) | `config/PasswordConfig` |
| SecurityFilterChain + `@EnableMethodSecurity` | `config/SecurityConfig` |
| Custom UserDetails | `security/CustomUserDetails` |
| Custom UserDetailsService | `security/CustomUserDetailsService` |
| Custom AuthenticationProvider | `security/BankAuthenticationProvider` |
| Custom 401 | `security/RestAuthenticationEntryPoint` |
| Custom 403 | `security/RestAccessDeniedHandler` |
| Read current user | `security/SecurityUtils` |
| Method-level security | `@PreAuthorize` in `service/*` |
| Ownership checks | `AccountService.getById`, `TransferService.getById / create` |
| Demo data | `config/DataInitializer`, `docs/bank_security.sql` |

URL rules (`SecurityConfig`):

* `POST /auth/register` is public
* `/admin/**` needs ROLE_ADMIN
* `/auth/me`, `/accounts/**`, `/transfers/**`, `/customers/**`, `/reports/**` need a login
* everything else is denied

Authentication failures (always an exception, never `null`):

| Problem | Exception | Result |
|---|---|---|
| Unknown email | `UsernameNotFoundException` -> `BadCredentialsException` | 401 |
| Wrong password | `BadCredentialsException` | 401 |
| Customer disabled | `DisabledException` | 401 |
| Customer blocked | `LockedException` | 401 |
| No role | `InsufficientAuthenticationException` | 401 |

The unknown-email and wrong-password messages are the same on purpose, so an attacker cannot find out which emails exist.

## 6. The complete flow

```text
Client
   |  HTTP request  (Authorization: Basic base64(email:password))
   v
SecurityFilterChain
   |  BasicAuthenticationFilter reads the header and builds an unauthenticated token
   v
AuthenticationManager (ProviderManager)
   |  asks its providers
   v
BankAuthenticationProvider
   |  calls CustomUserDetailsService
   v
CustomUserDetailsService  ->  CustomerRepository  ->  Database
   |  not found -> UsernameNotFoundException, disabled -> DisabledException, blocked -> LockedException
   v
PasswordEncoder (BCrypt)   raw password vs stored hash
   v
CustomUserDetails          customer data + authorities
   |  Roles        ->  ROLE_ADMIN
   |  Permissions  ->  ACCOUNT_READ, TRANSFER_APPROVE ...
   v
GrantedAuthority list      inside an authenticated token
   v
SecurityContext            the token is stored here for this request
   v
Authorization              URL rules (authorizeHttpRequests)
   v
Controller                 only receives the request and returns a DTO
   v
Service                    @PreAuthorize (permission) + ownership + business rules
   v
Repository -> Database
```

If something fails on the way:

* not logged in or bad credentials -> `RestAuthenticationEntryPoint` -> **401** JSON
* logged in but not allowed -> `RestAccessDeniedHandler` -> **403** JSON
* business rule broken (balance, amount ...) -> `GlobalExceptionHandler` -> **400 / 404 / 409** JSON

Responsibility of each component

| Component | Responsibility |
|---|---|
| SecurityFilterChain | Chain of filters; decides which URL needs what |
| BasicAuthenticationFilter | Reads the email and password from the request |
| AuthenticationManager | Gives the login to the right provider |
| BankAuthenticationProvider | Validates the login and builds the authenticated token |
| CustomUserDetailsService | Loads the customer and checks enabled / blocked |
| CustomerRepository | Reads the customer from the database |
| PasswordEncoder | Hashes passwords and compares raw password with the hash |
| CustomUserDetails | Holds the user data and authorities for Spring Security |
| GrantedAuthority | One permission string, e.g. `ROLE_ADMIN` or `ACCOUNT_READ` |
| SecurityContext | Holds the logged-in user during the request |
| Controller | HTTP in / out, validation (`@Valid`), no business logic |
| Service | Business rules, `@PreAuthorize`, ownership checks |
| Database | Stores the data (passwords only as BCrypt) |

## 7. Test cases

All are automated in `src/test/java/.../SecurityRulesTest.java` (19 tests) and available as requests in the Postman collection.

| Case | Expected |
|---|---|
| Correct email + password | 200 |
| Wrong password | 401 |
| Unknown email | 401 |
| Disabled / blocked customer | 401 |
| CUSTOMER calls ADMIN endpoint | 403 |
| BANK_MANAGER approves transfer | 200, status COMPLETED |
| Attack 1: register with `roles` | 400 |
| Attack 2: customer reads another customer's account | 403 |
| Attack 3: customer approves a transfer | 403 |
| Attack 4: no login on `/accounts/10` | 401 |
| Attack 5: another user's email in the body | ignored, the owner always comes from the SecurityContext (request DTOs have no owner field) |
| AUDITOR reads accounts / transfers / reports | 200 |
| AUDITOR creates / updates / deletes account, creates / approves transfer | 403 |
| Transfer with amount 0, same account, missing account, too much money | 400 / 400 / 404 / 400 |
