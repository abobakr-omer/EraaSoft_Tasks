package com.spring.demo.securebankingsystem.config;

import com.spring.demo.securebankingsystem.model.Account;
import com.spring.demo.securebankingsystem.model.Customer;
import com.spring.demo.securebankingsystem.model.Permission;
import com.spring.demo.securebankingsystem.model.Role;
import com.spring.demo.securebankingsystem.repo.AccountRepository;
import com.spring.demo.securebankingsystem.repo.CustomerRepository;
import com.spring.demo.securebankingsystem.repo.PermissionRepository;
import com.spring.demo.securebankingsystem.repo.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Runs once at startup and inserts the roles, permissions and demo users if they are missing.
 * It is safe to run many times.
 *
 * Demo users (password for all of them: Password@123)
 *   admin@bank.com    ADMIN
 *   manager@bank.com  BANK_MANAGER
 *   auditor@bank.com  AUDITOR
 *   ahmed@bank.com    CUSTOMER   (has 1 account)
 *   sara@bank.com     CUSTOMER   (has 1 account)
 *   eslam@bank.com    BANK_MANAGER + AUDITOR
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private static final String DEMO_PASSWORD = "Password@123";

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(PermissionRepository permissionRepository, RoleRepository roleRepository,
                           CustomerRepository customerRepository, AccountRepository accountRepository,
                           PasswordEncoder passwordEncoder) {
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) {
        createPermissions();

        createRole("ADMIN", "ACCOUNT_READ", "ACCOUNT_CREATE", "ACCOUNT_UPDATE", "ACCOUNT_DELETE",
                "TRANSFER_CREATE", "TRANSFER_READ", "TRANSFER_APPROVE",
                "CUSTOMER_READ", "CUSTOMER_UPDATE", "REPORT_VIEW");
        createRole("BANK_MANAGER", "ACCOUNT_READ", "ACCOUNT_UPDATE",
                "TRANSFER_READ", "TRANSFER_APPROVE", "CUSTOMER_READ", "CUSTOMER_UPDATE");
        createRole("CUSTOMER", "ACCOUNT_READ", "TRANSFER_CREATE", "TRANSFER_READ");
        createRole("AUDITOR", "ACCOUNT_READ", "TRANSFER_READ", "REPORT_VIEW");

        createCustomer("Admin", "admin@bank.com", "ADMIN");
        createCustomer("Bank Manager", "manager@bank.com", "BANK_MANAGER");
        createCustomer("Auditor", "auditor@bank.com", "AUDITOR");
        createCustomer("Eslam", "eslam@bank.com", "BANK_MANAGER", "AUDITOR");

        Customer ahmed = createCustomer("Ahmed", "ahmed@bank.com", "CUSTOMER");
        Customer sara = createCustomer("Sara", "sara@bank.com", "CUSTOMER");
        createAccount("ACC1000000001", ahmed, "5000.00");
        createAccount("ACC1000000002", sara, "3000.00");
    }

    private void createPermissions() {
        String[] names = {"ACCOUNT_READ", "ACCOUNT_CREATE", "ACCOUNT_UPDATE", "ACCOUNT_DELETE",
                "TRANSFER_CREATE", "TRANSFER_READ", "TRANSFER_APPROVE",
                "CUSTOMER_READ", "CUSTOMER_UPDATE", "REPORT_VIEW"};
        for (String name : names) {
            if (!permissionRepository.existsByName(name)) {
                Permission permission = new Permission();
                permission.setName(name);
                permissionRepository.save(permission);
            }
        }
    }

    private void createRole(String roleName, String... permissionNames) {
        if (roleRepository.existsByName(roleName)) {
            return;
        }
        Role role = new Role();
        role.setName(roleName);
        List<Permission> permissions = new ArrayList<>();
        for (String name : permissionNames) {
            permissions.add(permissionRepository.findByName(name).orElseThrow());
        }
        role.setPermissions(permissions);
        roleRepository.save(role);
    }

    private Customer createCustomer(String fullName, String email, String... roleNames) {
        return customerRepository.findByEmailIgnoreCase(email).orElseGet(() -> {
            Customer customer = new Customer();
            customer.setFullName(fullName);
            customer.setEmail(email);
            customer.setPassword(passwordEncoder.encode(DEMO_PASSWORD));
            for (String roleName : roleNames) {
                customer.getRoles().add(roleRepository.findByName(roleName).orElseThrow());
            }
            return customerRepository.save(customer);
        });
    }

    private void createAccount(String number, Customer owner, String balance) {
        if (accountRepository.existsByAccountNumber(number)) {
            return;
        }
        Account account = new Account();
        account.setAccountNumber(number);
        account.setCurrency("USD");
        account.setBalance(new BigDecimal(balance));
        account.setCustomer(owner);
        accountRepository.save(account);
    }
}
