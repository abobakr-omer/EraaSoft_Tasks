package com.item.service;

import com.item.model.Account;

public interface AccountService {

	// Creates a new account.
	boolean createAccount(Account account);

	// Checks if login credentials are correct.
	boolean authenticateAccount(Account account);

	// Gets safe profile details for one account.
	Account getAccountByUsername(String username);

	// Deletes an account by username.
	boolean deleteAccountByUsername(String username);

	// Resets password when username and email match.
	boolean resetPassword(String username,String email,String newPassword);
}
