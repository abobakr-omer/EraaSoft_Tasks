package com.ewallet.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.ewallet.model.Account;
import com.ewallet.service.AccountService;

// Uses JDBC to perform account operations in Oracle.
public class AccountServiceImpl implements AccountService {

	private final DataSource dataSource;
	
	public AccountServiceImpl(DataSource dataSource) {
		this.dataSource=dataSource;
	}
	
	@Override
	public boolean usernameExists(String username) {
		// UPPER makes the comparison case-insensitive.
		String query = "SELECT 1 FROM accounts WHERE UPPER(username)=UPPER(?)";

		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean phoneNumberExists(String phoneNumber) {
		String query = "SELECT 1 FROM accounts WHERE phone_number=?";

		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, phoneNumber);

			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean createAccount(Account account) {
		// Balance is omitted because Oracle gives it the default value zero.
		String query = "INSERT INTO accounts "
				+ "(username,password,age,phone_number) VALUES(?,?,?,?)";

		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, account.getUsername());
			statement.setString(2, account.getPassword());
			statement.setInt(3, account.getAge());
			statement.setString(4, account.getPhoneNumber());

			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean authenticateAccount(String username, String password) {
		// A returned row means both username and password matched.
		String query = "SELECT 1 FROM accounts "
				+ "WHERE UPPER(username)=UPPER(?) AND password=?";

		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, username);
			statement.setString(2, password);

			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Account findByUsername(String username) {
		// Load fresh account details from Oracle.
		String query = "SELECT id,username,age,phone_number,balance "
				+ "FROM accounts WHERE UPPER(username)=UPPER(?)";

		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, username);

			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					Account account = new Account();
					account.setId(resultSet.getLong("id"));
					account.setUsername(resultSet.getString("username"));
					account.setAge(resultSet.getInt("age"));
					account.setPhoneNumber(
							resultSet.getString("phone_number"));
					account.setBalance(resultSet.getDouble("balance"));
					return account;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean changePassword(
			String username, String oldPassword, String newPassword) {
		// The old password in WHERE prevents an incorrect password change.
		String query = "UPDATE accounts SET password=? "
				+ "WHERE UPPER(username)=UPPER(?) AND password=?";

		try (Connection connection = dataSource.getConnection();
			 PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, newPassword);
			statement.setString(2, username);
			statement.setString(3, oldPassword);

			return statement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
