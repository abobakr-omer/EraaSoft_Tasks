package eWalletSystem.service;

import eWalletSystem.model.Account;

import java.util.List;

public interface AccountService {

    /**
     * Creates a new account in the system.
     *
     * @param account the account to be created
     * @return the created Account if signup succeeds,
     *         or null if the username already exists
     */
    Account createAccount(Account account);

    /**
     * Checks login credentials and returns the matched account.
     *
     * @param account the account object containing username and password
     * @return the matched Account if login succeeds,
     *         or null if username or password is incorrect
     */
    Account authenticateAccount(Account account);


    /**
     * Adds money to the account balance.
     *
     * @param account the logged-in account
     * @param amount the deposit amount
     * @throws Exception if the amount is invalid or the account does not exist
     */
    void deposit(Account account, double amount) throws Exception;


    void withdraw(Account account,double amount) throws Exception;

    boolean changePassword(Account account,String oldPassword,String newPassword);

    void removeAccount(Account account) throws Exception;

    Account transfer(Account accountFrom, String receiverUserName, double amount);

    Account findAccountByUserName(String userName);

    void createDefaultAdmin();

    List<Account> getAllAccounts();




}
