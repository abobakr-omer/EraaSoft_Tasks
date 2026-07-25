package com.ewallet.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.ewallet.service.WalletService;

// Uses JDBC to update and read wallet balances.
public class WalletServiceImpl implements WalletService {

    private final DataSource dataSource;

    public WalletServiceImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean deposit(String username, double amount) {
        // Add the amount directly inside Oracle.
        String query = "UPDATE accounts "
                + "SET balance = balance + ? "
                + "WHERE UPPER(username) = UPPER(?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, amount);
            statement.setString(2, username);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean withdraw(String username, double amount) {
        // The balance condition prevents a negative balance.
        String query = "UPDATE accounts "
                + "SET balance = balance - ? "
                + "WHERE UPPER(username) = UPPER(?) "
                + "AND balance >= ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setDouble(1, amount);
            statement.setString(2, username);
            statement.setDouble(3, amount);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String transfer(
            String sourceUsername,
            String destinationUsername,
            double amount) {

        // All transfer statements use the same database connection.
        String destinationQuery =
                "SELECT 1 FROM accounts WHERE UPPER(username)=UPPER(?)";
        String withdrawQuery = "UPDATE accounts "
                + "SET balance=balance-? "
                + "WHERE UPPER(username)=UPPER(?) AND balance>=?";
        String depositQuery = "UPDATE accounts "
                + "SET balance=balance+? "
                + "WHERE UPPER(username)=UPPER(?)";

        try (Connection connection = dataSource.getConnection()) {
            // Disable automatic commits so both balance updates act as one unit.
            connection.setAutoCommit(false);

            try {
                try (PreparedStatement destinationStatement =
                             connection.prepareStatement(destinationQuery)) {
                    destinationStatement.setString(1, destinationUsername);

                    try (ResultSet resultSet =
                                 destinationStatement.executeQuery()) {
                        if (!resultSet.next()) {
                            connection.rollback();
                            return "Destination account does not exist.";
                        }
                    }
                }

                try (PreparedStatement withdrawStatement =
                             connection.prepareStatement(withdrawQuery)) {
                    withdrawStatement.setDouble(1, amount);
                    withdrawStatement.setString(2, sourceUsername);
                    withdrawStatement.setDouble(3, amount);

                    if (withdrawStatement.executeUpdate() == 0) {
                        connection.rollback();
                        return "Insufficient balance.";
                    }
                }

                try (PreparedStatement depositStatement =
                             connection.prepareStatement(depositQuery)) {
                    depositStatement.setDouble(1, amount);
                    depositStatement.setString(2, destinationUsername);

                    if (depositStatement.executeUpdate() == 0) {
                        connection.rollback();
                        return "Destination account does not exist.";
                    }
                }

                // Save both balance changes only after both succeed.
                connection.commit();
                return null;
            } catch (SQLException e) {
                // Undo partial changes when any transfer statement fails.
                connection.rollback();
                e.printStackTrace();
                return "Transfer could not be completed.";
            } finally {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Transfer could not be completed.";
        }
    }

    @Override
    public Double getBalance(String username) {
        // Returns null when the account cannot be found or read.
        String query = "SELECT balance FROM accounts "
                + "WHERE UPPER(username) = UPPER(?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("balance");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
