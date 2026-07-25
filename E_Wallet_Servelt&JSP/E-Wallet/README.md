# E-Wallet (Servlet, JSP, Oracle)

This project is built one phase at a time using Java 17, Tomcat 9, Servlets,
JSP, Oracle Database, and DBeaver.

## Current progress

- Phase 1: signup implemented
- Phase 2: login and three-attempt limit implemented
- Phase 3: authenticated wallet menu implemented
- Phase 4: deposit implemented
- Phase 5: withdraw implemented
- Phase 6: transactional transfer implemented
- Phase 7: change password implemented
- Phase 8: account details implemented
- Phase 9: logout implemented
- Phase 10: validation and controller error handling implemented

## Phase 1 flow

1. The browser sends the signup form to `SignupServlet`.
2. `Validator` checks the username, password, age, and Egyptian phone number.
3. `AccountDao` checks whether the username or phone number already exists.
4. The password is hashed before it is stored.
5. Oracle inserts the account with a starting balance of zero.
6. Oracle unique constraints provide final protection against duplicates.

## One-time Oracle setup

1. Create an Oracle user/schema named `ewallet`, or use an existing schema.
2. Open DBeaver and connect to that schema.
3. Run `database/schema.sql`.
4. Download the Oracle JDBC 11 driver (`ojdbc11.jar`) and copy it into:
   `src/main/webapp/WEB-INF/lib`.

The default connection is:

```text
jdbc:oracle:thin:@localhost:1521/XEPDB1
username: ewallet
```

Configure these environment variables in Eclipse:

```text
EWALLET_DB_URL=jdbc:oracle:thin:@localhost:1521/XEPDB1
EWALLET_DB_USER=ewallet
EWALLET_DB_PASSWORD=your_password
```

In Eclipse, open the Tomcat server launch configuration and add them on the
**Environment** tab. Do not put a real password in the Java source.

## Run Phase 1

1. Refresh the project in Eclipse.
2. Clean the project using **Project > Clean**.
3. Add the project to Tomcat 9.
4. Start Tomcat.
5. Open `http://localhost:8080/E-Wallet/`.

Try a valid signup:

```text
Username: Ahmed1
Password: Wallet123
Age: 20
Phone: 01012345678
```

Try the same username or phone again to confirm duplicate prevention.

## Planned order

1. Signup
2. Login and invalid-attempt limit
3. Authenticated menu
4. Deposit
5. Withdraw
6. Transfer (using a database transaction)
7. Change password
8. Account details
9. Logout
10. Error handling and final testing
