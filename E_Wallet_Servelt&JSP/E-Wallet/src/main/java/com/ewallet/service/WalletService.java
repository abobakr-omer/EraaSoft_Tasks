package com.ewallet.service;

// Lists all balance and money-transfer operations.
public interface WalletService {

    boolean deposit(String username, double amount);

    boolean withdraw(String username, double amount);

    String transfer(
            String sourceUsername,
            String destinationUsername,
            double amount);

    Double getBalance(String username);
}
