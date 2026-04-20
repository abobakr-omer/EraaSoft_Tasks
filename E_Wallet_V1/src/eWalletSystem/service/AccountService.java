package eWalletSystem.service;

import eWalletSystem.model.Account;

public interface AccountService {

    /**
     * Create a new account if the username does not already exist.
     */
    Boolean createAccount(Account account);

    /**
     * Check if an account exists with the same username and password.
     */
    Boolean isAccountExistByUserNameAndPassword(Account account);



}
