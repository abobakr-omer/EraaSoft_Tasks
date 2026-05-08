package eWalletSystem.service.impl;

import eWalletSystem.exception.*;
import eWalletSystem.model.Account;
import eWalletSystem.model.EWalletSystem;
import eWalletSystem.service.AccountService;
import eWalletSystem.service.ValidationService;

import java.util.List;
import java.util.Objects;

public class AccountServiceImpl implements AccountService {

    private EWalletSystem eWalletSystem = new EWalletSystem();
    private ValidationService validationService = new ValidationServiceImpl();
    private final String adminUserName = "IAM";
    private final String adminPassword = "IAM123";


    // Private helper method for searching by username and password
    private Account findAccountByUserNameAndPassword(String userName, String password) {
        return eWalletSystem.getAccounts().stream()
                .filter(account -> account.getUserName().equals(userName)
                        && account.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    private Account findAccountByPhoneNumber(String phoneNumber) {
        return eWalletSystem.getAccounts().stream()
                .filter(account -> Objects.equals(account.getPhoneNumber(), phoneNumber))
                .findFirst()
                .orElse(null);
    }

    // Private helper method for searching by username
    public Account findAccountByUserName(String userName) {
        return eWalletSystem.getAccounts().stream()
                .filter(account -> account.getUserName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Account createAccount(Account account) {
        Account existingAccount = findAccountByUserName(account.getUserName());
        Account existingPhoneNumber = findAccountByPhoneNumber(account.getPhoneNumber());

        // If username already exists → do not create account
        if (existingAccount != null) {
            return null;
        }

        // Do not create account if phone number already exists
        if (existingPhoneNumber != null) {
            return null;
        }

        // Add the new account to the system
        eWalletSystem.getAccounts().add(account);

        return account;
    }

    @Override
    public Account authenticateAccount(Account account) {
        if (Objects.isNull(account)) {
            return null;
        }

        return findAccountByUserNameAndPassword(
                account.getUserName(),
                account.getPassword()
        );
    }


//    @Override
//    public Boolean deposit(Account account, double amount) {
//
//        // TODO amount must be 100 200 300 400 ...... etc
//        if (amount < 100) {
//            return false;
//        }
//
//        // []
//        // [0,1,2,3,4]
//        int index = IntStream.range(0, eWalletSystem.getAccounts().size())
//                .filter(i -> {
//                    Account acc = eWalletSystem.getAccounts().get(i);
//                    return acc.getUserName().equals(account.getUserName()) &&
//                            acc.getPassword().equals(account.getPassword());
//                })
//                .findFirst()
//                .orElse(-1);
//
//        if (index == -1) {
//            return false;
//        }
//
//        double totalBalance = eWalletSystem.getAccounts().get(index).getBalance() + amount;
//        eWalletSystem.getAccounts().get(index).setBalance(totalBalance);
//
//        return true;
//    }


    @Override
    public void deposit(Account account, double amount) throws Exception {

        // Deposit amount must be at least 100
        // and must be a multiple of 100  100, 200, 300
        if (amount < 100 || amount % 100 != 0) {
            throw new InvalidDepositAmountException
                    ("Deposit amount must be 100 or more and in multiples of 100.");
        }

        // Account object must not be null
        if (Objects.isNull(account)) {
            throw new Exception("Account is required.");
        }

        // Check if the account exists in the system
        Account accountExist = authenticateAccount(account);

        if (Objects.isNull(accountExist)) {
            throw new AuthenticationException("Account does not exist.");
        }

        // Add the amount to the current balance
        double totalBalance = accountExist.getBalance() + amount;
        accountExist.setBalance(totalBalance);
    }

    @Override
    public void withdraw(Account account, double amount) throws Exception {

        // Withdraw amount must be at least 100
        // and must be a multiple of 100 like 100, 200, 300...
        if (amount < 100 || amount % 100 != 0) {
            throw new InvalidWithdrawAmountException(
                    "Withdraw amount must be 100 or more and in multiples of 100."
            );
        }

        // The account object must not be null
        if (Objects.isNull(account)) {
            throw new Exception("Account is required.");
        }

        // Check if the account exists in the system
        Account accountExist = authenticateAccount(account);

        if (Objects.isNull(accountExist)) {
            throw new Exception("Account does not exist.");
        }

        // Check if the real stored account has enough balance
        if (accountExist.getBalance() < amount) {
            throw new InvalidWithdrawAmountException(
                    "Insufficient balance. Withdraw amount must be less than or equal to your current balance."
            );
        }

        // Deduct the amount from the real stored account balance
        double totalAmount = accountExist.getBalance() - amount;
        accountExist.setBalance(totalAmount);
    }

    @Override
    public boolean changePassword(Account account, String oldPassword, String newPassword) {
        // Check if account exists in the system
        Account accountExist = authenticateAccount(account);

        if (Objects.isNull(accountExist)) {
            throw new AuthenticationException("Account does not exist.");
        }

        // Old password must match current password
        if (!accountExist.getPassword().equals(oldPassword)) {
            return false;
        }

        // New password must be different from old password
        if (oldPassword.equals(newPassword)) {
            return false;
        }

        // New password must follow password validation rules
        if (!validationService.isPasswordValid(newPassword)) {
            return false;
        }

        // Update password
        accountExist.setPassword(newPassword);
        return true;
    }

    @Override
    public void removeAccount(Account account) throws Exception {
        // Account input must not be null
        if (Objects.isNull(account)) {
            throw new Exception("Account is required.");
        }

        // Check if the account exists in the system
        Account accountExist = authenticateAccount(account);

        if (Objects.isNull(accountExist)) {
            throw new Exception("Account does not exist.");
        }

        // Remove the real stored account from the system
        eWalletSystem.getAccounts().remove(accountExist);
    }

    @Override
    public Account transfer(Account accountFrom, String receiverUserName, double amount) {
        // Sender account must not be null
        if (Objects.isNull(accountFrom)) {
            throw new AuthenticationException("Sender account is required.");
        }

        // Receiver username must not be null or blank
        if (receiverUserName == null || receiverUserName.isBlank()) {
            throw new AuthenticationException("Receiver username is required.");
        }

        // Transfer amount must be at least 100
        // and must be in multiples of 100 like 100, 200, 300...
        if (amount < 100 || amount % 100 != 0) {
            throw new InvalidAmountException(
                    "Transfer amount must be 100 or more and in multiples of 100."
            );
        }

        // Authenticate sender account in the system
        Account accountExistFrom = authenticateAccount(accountFrom);

        if (Objects.isNull(accountExistFrom)) {
            throw new AuthenticationException("The sender account does not exist.");
        }

        // Find receiver account by username only
        Account accountExistTo = findAccountByUserName(receiverUserName);

        if (Objects.isNull(accountExistTo)) {
            throw new AuthenticationException("The receiver account does not exist.");
        }

        // Prevent transfer to the same account
        if (accountExistFrom.getUserName().equals(accountExistTo.getUserName())) {
            throw new InvalidAmountException("You cannot transfer money to the same account.");
        }

        // Check sender balance
        if (accountExistFrom.getBalance() < amount) {
            throw new InvalidAmountException(
                    "Insufficient balance. Transfer amount must be less than or equal to your current balance."
            );
        }

        // Update the real stored accounts
        double totalAmountFrom = accountExistFrom.getBalance() - amount;
        double totalAmountTo = accountExistTo.getBalance() + amount;

        accountExistFrom.setBalance(totalAmountFrom);
        accountExistTo.setBalance(totalAmountTo);

        // Return receiver account so application layer can show updated receiver balance
        return accountExistTo;
    }

    @Override
    public void createDefaultAdmin() {
        Account existingAdmin = findAccountByUserName("IAM");

        if (existingAdmin == null) {
            Account adminAccount = new Account(adminUserName, adminPassword, true);
            eWalletSystem.getAccounts().add(adminAccount);
        }
    }

    @Override
    public List<Account> getAllAccounts(){
        return eWalletSystem.getAccounts();
    }


}
