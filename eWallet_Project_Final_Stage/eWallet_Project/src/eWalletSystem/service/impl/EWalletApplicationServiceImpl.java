package eWalletSystem.service.impl;

import eWalletSystem.exception.*;
import eWalletSystem.model.Account;
import eWalletSystem.model.EWalletSystem;
import eWalletSystem.service.AccountService;
import eWalletSystem.service.ApplicationService;
import eWalletSystem.service.ValidationService;

import java.util.*;

/**
 * Handles the main application flow for the E-Wallet system,
 * including the start menu, signup, login, and profile menu.
 */
public class EWalletApplicationServiceImpl implements ApplicationService {

   private final String adminUserName="IAM";
   private final String adminPassword="IAM123";

    private final AccountService accountService = new AccountServiceImpl();
    private final ValidationService validationService=new ValidationServiceImpl();
    private final Scanner scanner = new Scanner(System.in);
    private boolean isAdmin=false;

    /**
     * Starts the application and shows the main menu.
     * The application continues until the user exits
     * or exceeds the allowed invalid attempts.
     */
    @Override
    public void start() {
        accountService.createDefaultAdmin();

        System.out.println("=================================");
        System.out.println("      Welcome to E-Wallet");
        System.out.println("=================================");

        int counter = 0;
        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("\nPlease select an option:");
                System.out.println("1. Login");
                System.out.println("2. Sign Up");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        signUp();
                        break;
                    case 3:
                        System.out.println("\nThank you for using E-Wallet.");
                        System.out.println("Goodbye!");
                        exit = true;
                        break;
                    default:
                        throw new  InvalidMenuChoiceException("Invalid choice. Please select 1, 2, or 3.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("\nInvalid input. Please enter numbers only.");
                scanner.next();
                counter++;
            }
            catch (InvalidMenuChoiceException e){
                System.out.println("\n" + e.getMessage());
                counter++;
            }
            catch (Exception e) {
                System.out.println("\nUnexpected error: " + e.getMessage());
            }


            if (counter == 4) {
                System.out.println("\nYou have exceeded the maximum number of invalid attempts.");
                System.out.println("Please contact the system administrator.");
                break;
            }
        }
    }

    /**
     * Collects signup from the user,
     * creates a new account object,
     * and sends it to the account service for creation.
     */
    private void signUp() {
        try {
            System.out.println("\n=================================");
            System.out.println("            SIGN UP              ");
            System.out.println("=================================");

            String userName;
            String password;
            String phoneNumber;
            Integer age;

            // Username loop
            while (true) {
                System.out.println("\nUsername Rules:");
                System.out.println("- At least 3 characters");
                System.out.println("- Must start with an uppercase letter");
                System.out.println("- Must not contain numbers");
                System.out.print("Enter username: ");

                userName = scanner.next();

                if (validationService.isUserNameValid(userName)) {
                    break;
                }

                System.out.println("\n[Invalid Username]");
                System.out.println("Please enter a valid username.");
            }

            // Password loop
            while (true) {
                System.out.println("\nPassword Rules:");
                System.out.println("- At least 8 characters");
                System.out.println("- Must start with a letter");
                System.out.println("- Must contain uppercase and lowercase letters");
                System.out.println("- Must contain at least one digit");
                System.out.println("- Must contain at least one special character");
                System.out.println("- Must not contain spaces");
                System.out.print("Enter password: ");

                password = scanner.next();

                if (validationService.isPasswordValid(password)) {
                    break;
                }

                System.out.println("\n[Invalid Password]");
                System.out.println("Please enter a valid password.");
            }

            // Phone number loop
            while (true) {
                System.out.println("\nPhone Number Rules:");
                System.out.println("- Must be in Egyptian format");
                System.out.println("- Must start with +20");
                System.out.println("- Valid prefixes: 10, 11, 12, 15");
                System.out.println("- Total length must be 13 characters");
                System.out.print("Enter phone number: ");

                phoneNumber = scanner.next();

                if (validationService.isPhoneNumberValid(phoneNumber)) {
                    break;
                }

                System.out.println("\n[Invalid Phone Number]");
                System.out.println("Please enter a valid Egyptian phone number.");
            }

            // Age loop
            while (true) {
                try {
                    System.out.println("\nAge Rules:");
                    System.out.println("- Age must be 18 or above");
                    System.out.print("Enter age: ");

                    age = scanner.nextInt();

                    if (validationService.isAgeValid(age)) {
                        break;
                    }

                    System.out.println("\n[Invalid Age]");
                    System.out.println("Age must be 18 or above.");

                } catch (InputMismatchException e) {
                    scanner.next();
                    System.out.println("\n[Invalid Input]");
                    System.out.println("Please enter age as a number only.");
                }
            }

            // Try to create account
            while (true) {
                Account account = new Account(userName, password, phoneNumber, age);
                account = accountService.createAccount(account);

                if (Objects.nonNull(account)) {
                    System.out.println("\n=================================");
                    System.out.println("   Account created successfully  ");
                    System.out.println("=================================");
                    System.out.println("Welcome, " + account.getUserName() + "!");
                    System.out.println("You can now access your profile.");
                    mainProfile(account);
                    return;
                }

                System.out.println("\n[Signup Failed]");
                System.out.println("Username or phone number already exists.");
                System.out.println("Please enter new username and phone number.");

                // Retry username only
                while (true) {
                    System.out.print("Enter username: ");
                    userName = scanner.next();

                    if (validationService.isUserNameValid(userName)) {
                        break;
                    }

                    System.out.println("\n[Invalid Username]");
                    System.out.println("Please enter a valid username.");
                }

                // Retry phone only
                while (true) {
                    System.out.print("Enter phone number: ");
                    phoneNumber = scanner.next();

                    if (validationService.isPhoneNumberValid(phoneNumber)) {
                        break;
                    }

                    System.out.println("\n[Invalid Phone Number]");
                    System.out.println("Please enter a valid Egyptian phone number.");
                }
            }

        } catch (Exception e) {
            System.out.println("\n[Unexpected Error]");
            System.out.println("An unexpected error occurred during sign up.");
        }
    }

//    private void signUp() {
//        try {
//            System.out.println("\n=================================");
//            System.out.println("            SIGN UP              ");
//            System.out.println("=================================");
//
//            // Username input + rules
//            System.out.println("\nUsername Rules:");
//            System.out.println("- At least 3 characters");
//            System.out.println("- Must start with an uppercase letter");
//            System.out.println("- Must not contain numbers");
//            System.out.print("Enter username: ");
//            String userName = scanner.next();
//
//            boolean isUserNameValid = validationService.isUserNameValid(userName);
//            if (!isUserNameValid) {
//                System.out.println("\n[Invalid Username]");
//                System.out.println("Username must be at least 3 characters, start with an uppercase letter, and must not contain numbers.");
//                return;
//            }
//
//            // Password input + rules
//            System.out.println("\nPassword Rules:");
//            System.out.println("- At least 8 characters");
//            System.out.println("- Must start with a letter");
//            System.out.println("- Must contain uppercase and lowercase letters");
//            System.out.println("- Must contain at least one digit");
//            System.out.println("- Must contain at least one special character");
//            System.out.println("- Must not contain spaces");
//            System.out.print("Enter password: ");
//            String password = scanner.next();
//
//            boolean isPasswordValid = validationService.isPasswordValid(password);
//            if (!isPasswordValid) {
//                System.out.println("\n[Invalid Password]");
//                System.out.println("Password must be at least 8 characters, start with a letter, include uppercase and lowercase letters, at least one digit, one special character, and no spaces.");
//                return;
//            }
//
//            // Phone number input + rules
//            System.out.println("\nPhone Number Rules:");
//            System.out.println("- Must be in Egyptian format");
//            System.out.println("- Must start with +20");
//            System.out.println("- Valid prefixes: 10, 11, 12, 15");
//            System.out.println("- Total length must be 13 characters");
//            System.out.print("Enter phone number: ");
//            String phoneNumber = scanner.next();
//
//            boolean isPhoneNumberValid = validationService.isPhoneNumberValid(phoneNumber);
//            if (!isPhoneNumberValid) {
//                System.out.println("\n[Invalid Phone Number]");
//                System.out.println("Phone number must be in Egyptian format, start with +20, and be 13 characters long.");
//                return;
//            }
//
//            // Age input + rules
//            System.out.println("\nAge Rules:");
//            System.out.println("- Age must be 18 or above");
//            System.out.print("Enter age: ");
//            Integer age = scanner.nextInt();
//
//            boolean isAgeValid = validationService.isAgeValid(age);
//            if (!isAgeValid) {
//                System.out.println("\n[Invalid Age]");
//                System.out.println("Age must be 18 or above.");
//                return;
//            }
//
//            // Create account after all validations pass
//            Account account = new Account(userName, password, phoneNumber, age);
//
//            // Save account in the system
//            account = accountService.createAccount(account);
//
//            if (Objects.isNull(account)) {
//                System.out.println("\n[Signup Failed]");
//                System.out.println("An account with the same username or phone number already exists. Please try different details.");                return;
//            }
//
//            System.out.println("\n=================================");
//            System.out.println("   Account created successfully  ");
//            System.out.println("=================================");
//            System.out.println("Welcome, " + account.getUserName() + "!");
//            System.out.println("You can now access your profile.");
//
//            mainProfile(account);
//
//        } catch (InputMismatchException e) {
//            scanner.next();
//            System.out.println("\n[Invalid Input]");
//            System.out.println("Please enter the correct data type.");
//
//        } catch (Exception e) {
//            System.out.println("\n[Unexpected Error]");
//            System.out.println("An unexpected error occurred during sign up.");
//        }
//    }
//
    /**
     * Collects login data from the user
     * and checks if the account exists in the system.
     */
    private void login() {
        int maxAttemps = 0;

        while (maxAttemps < 4) {
            try {
                System.out.println("\n========== Login ==========");

                System.out.print("Enter username: ");
                String userName = scanner.next();

                System.out.print("Enter password: ");
                String password = scanner.next();

                if (userName.isBlank() || password.isBlank()) {
                    System.out.println("\n[Login Failed]");
                    System.out.println("Username and password are required.");
                    maxAttemps++;
                    continue;
                }

                Account account = new Account(userName, password);
                account = accountService.authenticateAccount(account);

                if (Objects.nonNull(account)) {
                    System.out.println("\nLogin successful.");
                    System.out.println("Welcome back, " + account.getUserName() + "!");

                    if (account.isAdmin()) {
                        mainProfileAdmin(account);
                    } else {
                        mainProfile(account);
                    }

                    return;
                }

                System.out.println("\n[Login Failed]");
                System.out.println("Invalid username or password. Please try again.");
                maxAttemps++;

            } catch (AuthenticationException e) {
                System.out.println("\n" + e.getMessage());
                maxAttemps++;
            } catch (Exception e) {
                System.out.println("\nAn unexpected error occurred during login.");
                maxAttemps++;
            }
        }

        System.out.println("\nYou have exceeded the maximum number of login attempts.");
        System.out.println("Please return to the main menu.");
    }

    /**
     * Shows the main profile menu after successful login or signup.
     */
    private void mainProfile(Account account) {

        boolean logOut = false;

        while (!logOut) {
            try {
                System.out.println("\n========== Main Menu ==========");
                System.out.println("[1] Deposit");
                System.out.println("[2] Withdraw");
                System.out.println("[3] Transfer");
                System.out.println("[4] Show Profile Details");
                System.out.println("[5] Change Password");
                System.out.println("[6] Remove Account");
                System.out.println("[7] Logout");
                System.out.print("Enter your choice: ");

                int feature = scanner.nextInt();

                switch (feature) {
                    case 1:
                        handleDeposit(account);
                        break;
                    case 2:
                        handleWithdraw(account);
                        break;
                    case 3:
                        handleTransfer(account);
                        break;
                    case 4:
                        showProfileDetails(account);
                        break;
                    case 5:
                        handleChangePassword(account);
                        break;
                    case 6:
                        logOut = handleRemoveAccount(account);
                        break;
                    case 7:
                        System.out.println("\nYou have logged out successfully.");
                        System.out.println("Returning to the main application menu...");
                        logOut = true;
                        break;
                    default:
                        System.out.println("\n[Invalid Choice]");
                        System.out.println("Please select a valid option from 1 to 7.");
                }

            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("\n[Invalid Input]");
                System.out.println("Please enter numbers only.");
            } catch (Exception e) {
                System.out.println("\n[Main Menu Error]");
                System.out.println(e.getMessage());
            }
        }
        }

    private void mainProfileAdmin(Account adminAccount) {
        boolean logout = false;

        while (!logout) {
            try {
                System.out.println("\n=================================");
                System.out.println("           ADMIN PANEL           ");
                System.out.println("=================================");
                System.out.println("[1] View All Accounts");
                System.out.println("[2] Search Account By Username");
                System.out.println("[3] Logout");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        showAllAccounts();
                        break;

                    case 2:
                        System.out.print("Enter username: ");
                        String accountUserName = scanner.next();

                        Account account = accountService.findAccountByUserName(accountUserName);

                        if (Objects.isNull(account)) {
                            System.out.println("\n[Account Not Found]");
                            System.out.println("No account exists with this username.");
                        } else {
                            showProfileDetails(account);
                        }
                        break;

                    case 3:
                        System.out.println("\nAdmin logged out successfully.");
                        logout = true;
                        break;

                    default:
                        System.out.println("\n[Invalid Choice]");
                        System.out.println("Please select a valid option from 1 to 3.");
                }

            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("\n[Invalid Input]");
                System.out.println("Please enter numbers only.");
            } catch (Exception e) {
                System.out.println("\n[Admin Panel Error]");
                System.out.println(e.getMessage());
            }
        }
    }

    private void showProfileDetails(Account account){
        System.out.println("=================================");
        System.out.println("           Account Data          ");
        System.out.println("=================================\n");

        System.out.println("Username: "+account.getUserName());
        System.out.println("Password: "+account.getPassword());
        System.out.println("Balance: "+account.getBalance());
        System.out.println("Phone Number: "+account.getPhoneNumber());
        System.out.println("Age: "+account.getAge());

    }



    private void handleDeposit(Account account){
        try {
            System.out.println("\n=================================");
            System.out.println("             DEPOSIT             ");
            System.out.println("=================================");
            System.out.println("Deposit Rules:");
            System.out.println("- Amount must be 100 or more");
            System.out.println("- Amount must be in multiples of 100");
            System.out.print("Enter amount to deposit: ");

            double amount = scanner.nextDouble();

            accountService.deposit(account, amount);

            System.out.println("\nDeposit completed successfully.");
            System.out.println("Deposited amount: " + amount);
            System.out.println("Updated balance: " + account.getBalance());

        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("\n[Invalid Input]");
            System.out.println("Please enter a valid numeric amount.");

        } catch (InvalidDepositAmountException e) {
            System.out.println("\n[Deposit Failed]");
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println("\n[Deposit Failed]");
            System.out.println(e.getMessage());
        }


    }

    private void handleWithdraw(Account account){
        try {
            System.out.println("\n=================================");
            System.out.println("            WITHDRAW             ");
            System.out.println("=================================");
            System.out.println("Withdraw Rules:");
            System.out.println("- Amount must be 100 or more");
            System.out.println("- Amount must be in multiples of 100");
            System.out.print("Enter amount to withdraw: ");

            double amount = scanner.nextDouble();

            accountService.withdraw(account, amount);

            System.out.println("\nWithdraw completed successfully.");
            System.out.println("Withdrawn amount: " + amount);
            System.out.println("Updated balance: " + account.getBalance());

        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("\n[Invalid Input]");
            System.out.println("Please enter a valid numeric amount.");

        } catch (InvalidWithdrawAmountException e) {
            System.out.println("\n[Withdraw Failed]");
            System.out.println(e.getMessage());

        } catch (Exception e) {
            System.out.println("\n[Withdraw Failed]");
            System.out.println(e.getMessage());
        }


    }

    private void handleChangePassword(Account account){

        try {
            System.out.println("\n=================================");
            System.out.println("         CHANGE PASSWORD         ");
            System.out.println("=================================");

            System.out.print("Enter old password: ");
            String oldPassword = scanner.next();

            System.out.println("\nNew Password Rules:");
            System.out.println("- At least 8 characters");
            System.out.println("- Must start with a letter");
            System.out.println("- Must contain uppercase and lowercase letters");
            System.out.println("- Must contain at least one digit");
            System.out.println("- Must contain at least one special character");
            System.out.println("- Must not contain spaces");
            System.out.print("Enter new password: ");
            String newPassword = scanner.next();

            boolean isPasswordChanged = accountService.changePassword(account, oldPassword, newPassword);

            if (isPasswordChanged) {
                System.out.println("\nPassword changed successfully.");
            } else {
                System.out.println("\nPassword change failed.");
                System.out.println("Please make sure:");
                System.out.println("- Old password is correct");
                System.out.println("- New password is different from old password");
                System.out.println("- New password follows all password rules");
            }

        } catch (AuthenticationException e) {
            System.out.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred while changing the password.");
        }


    }

    private boolean handleRemoveAccount(Account account) {
        try {
            System.out.println("\n=================================");
            System.out.println("         REMOVE ACCOUNT          ");
            System.out.println("=================================");
            System.out.println("Warning: This action will permanently remove your account.");
            System.out.print("Do you want to continue? (Y/N): ");

            String confirm = scanner.next();

            if (!confirm.equalsIgnoreCase("Y")) {
                System.out.println("\nAccount removal has been cancelled.");
                return false;
            }

            accountService.removeAccount(account);

            System.out.println("\nYour account has been removed successfully.");
            System.out.println("Thank you for using E-Wallet.");
            System.out.println("Returning to the main application menu...");

            return true;

        } catch (Exception e) {
            System.out.println("\n[Account Removal Failed]");
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void handleTransfer(Account account) {
        try {
            System.out.println("\n=================================");
            System.out.println("             TRANSFER            ");
            System.out.println("=================================");
            System.out.println("Transfer Rules:");
            System.out.println("- Receiver username must exist");
            System.out.println("- You cannot transfer to your own account");
            System.out.println("- Amount must be 100 or more");
            System.out.println("- Amount must be in multiples of 100");

            System.out.print("Enter receiver username: ");
            String receiverUserName = scanner.next();

            System.out.print("Enter amount to transfer: ");
            double amount = scanner.nextDouble();

            Account receiverAccount = accountService.transfer(account, receiverUserName, amount);

            System.out.println("\nTransfer completed successfully.");
            System.out.println("Transferred amount: " + amount);
            System.out.println("Receiver username: " + receiverUserName);
            System.out.println("Your updated balance: " + account.getBalance());
            System.out.println("Receiver updated balance: " + receiverAccount.getBalance());

        } catch (InputMismatchException e) {
            scanner.next();
            System.out.println("\n[Invalid Input]");
            System.out.println("Please enter a valid numeric amount.");
        } catch (AuthenticationException e) {
            System.out.println("\n[Transfer Failed]");
            System.out.println(e.getMessage());
        } catch (InvalidAmountException e) {
            System.out.println("\n[Transfer Failed]");
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[Transfer Failed]");
            System.out.println("An unexpected error occurred during transfer.");
        }
    }

    private void showAllAccounts(){
        List<Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("\nNo accounts found in the system.");
            return;
        }

        System.out.println("\n=================================");
        System.out.println("           ALL ACCOUNTS          ");
        System.out.println("=================================");

        for (Account account : accounts) {
            System.out.println("Username: " + account.getUserName());
            System.out.println("Balance: " + account.getBalance());
            System.out.println("Phone Number: " + account.getPhoneNumber());
            System.out.println("Age: " + account.getAge());
            System.out.println("Admin: " + account.isAdmin());
            System.out.println("---------------------------------");
        }
    }



}