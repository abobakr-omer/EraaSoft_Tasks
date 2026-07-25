package com.ewallet.service;

import com.ewallet.model.Account;

// Lists all account operations that the controller can use.
public interface AccountService {
	
	boolean usernameExists(String username);
	
	boolean phoneNumberExists(String phoneNumber);
	
	boolean createAccount(Account account);
	
	boolean authenticateAccount(String username, String password);

	Account findByUsername(String username);

	boolean changePassword(
			String username, String oldPassword, String newPassword);
	
}
