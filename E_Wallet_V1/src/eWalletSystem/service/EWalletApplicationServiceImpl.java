package eWalletSystem.service;

import eWalletSystem.exception.AuthenticationException;
import eWalletSystem.exception.DuplicateAccountException;
import eWalletSystem.exception.InvalidMenuChoiceException;
import eWalletSystem.model.Account;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Handles the main application flow for the E-Wallet system,
 * including the start menu, signup, login, and profile menu.
 */
public class EWalletApplicationServiceImpl implements ApplicationService {

    private final AccountService accountService = new AccountServiceImpl();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Starts the application and shows the main menu.
     * The application continues until the user exits
     * or exceeds the allowed invalid attempts.
     */
    @Override
    public void start() {
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
            System.out.println("\n========== Sign Up ==========");

            System.out.print("Enter username: ");
            String userName = scanner.next();

            System.out.print("Enter password: ");
            String password = scanner.next();

            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.next();

            System.out.print("Enter age: ");
            float age = scanner.nextFloat();

            Account account = new Account(userName, password, phoneNumber, age);

            boolean isAccountCreated = accountService.createAccount(account);

            if (isAccountCreated) {
                System.out.println("\nYour account has been created successfully.");
                System.out.println("You can now access your profile.");
                mainProfile();
            } else {
                System.out.println("\nAccount creation failed.");
                System.out.println("An account with the same username already exists.");
                System.out.println("Please try again with a different username.");
            }
        }
        catch (InputMismatchException e){
            System.out.println("\nInvalid age. Please enter a valid number.");
        }catch (DuplicateAccountException e){
            System.out.println("\n" + e.getMessage());
        }
        catch (Exception e) {
            System.out.println("\nAn unexpected error occurred during sign up.");
        }
    }

    /**
     * Collects login data from the user
     * and checks if the account exists in the system.
     */
    private void login() {
        try {
            System.out.println("\n========== Login ==========");

            System.out.print("Enter username: ");
            String userName = scanner.next();

            System.out.print("Enter password: ");
            String password = scanner.next();

            Account account = new Account(userName, password);

            boolean isAccountExist = accountService.isAccountExistByUserNameAndPassword(account);

            if (isAccountExist) {
                System.out.println("\nLogin successful.");
                System.out.println("Welcome back, " + userName + "!");
                mainProfile();
            } else {
                System.out.println("\nLogin failed.");
                System.out.println("Invalid username or password. Please try again.");
            }
        }
        catch (AuthenticationException e) {
            System.out.println("\n" + e.getMessage());
        } catch (Exception e) {
            System.out.println("\nAn unexpected error occurred during login.");
        }
    }

    /**
     * Shows the main profile menu after successful login or signup.
     */
    private void mainProfile() {
        System.out.println("\n========== Main Profile ==========");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Logout");
        System.out.print("Enter your choice: ");
    }



}