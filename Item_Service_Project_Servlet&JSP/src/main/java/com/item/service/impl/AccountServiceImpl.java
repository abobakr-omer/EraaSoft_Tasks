package com.item.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.item.model.Account;
import com.item.service.AccountService;

public class AccountServiceImpl implements AccountService {

	private DataSource dataSource;

	// Receives the database connection pool from the controller.
	public AccountServiceImpl(DataSource dataSource) {
		this.dataSource=dataSource;
	}

	// Inserts a new account into the ACCOUNT table.
	@Override
	public boolean createAccount(Account account) {

		Connection connection=null;
		PreparedStatement statement=null;
		boolean isAccountCreated=false;

		String query="INSERT INTO account (USERNAME, EMAIL, PASSWORD) VALUES (?,?,?)";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setString(1,account.getUsername());
			statement.setString(2,account.getEmail());
			statement.setString(3,account.getPassword());

			int isRowCreated=statement.executeUpdate();
			isAccountCreated=isRowCreated>0;

		} catch(SQLException e) {
			System.out.println("Exception "+e.getMessage());

		} finally {
			try {
				if(statement!=null) {
					statement.close();
				}

				if(connection!=null) {
					connection.close();
				}

			} catch(SQLException e) {
				System.out.println("Exception "+e.getMessage());
			}
		}

		return isAccountCreated;
	}

	// Checks if the username and password match an account in the database.
	@Override
	public boolean authenticateAccount(Account account) {

		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultSet=null;
		boolean isAuthenticated=false;

		String query="SELECT * FROM account WHERE USERNAME=? AND PASSWORD=?";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setString(1,account.getUsername());
			statement.setString(2,account.getPassword());

			resultSet=statement.executeQuery();
			isAuthenticated=resultSet.next();

		} catch(SQLException e) {
			System.out.println("Exception "+e.getMessage());

		} finally {
			try {
				if(resultSet!=null) {
					resultSet.close();
				}

				if(statement!=null) {
					statement.close();
				}

				if(connection!=null) {
					connection.close();
				}

			} catch(SQLException e) {
				System.out.println("Exception "+e.getMessage());
			}
		}

		return isAuthenticated;
	}

	@Override
	public Account getAccountByUsername(String username) {
		String query="SELECT ID, USERNAME, EMAIL FROM account WHERE USERNAME=?";

		try(Connection connection=dataSource.getConnection();
				PreparedStatement statement=connection.prepareStatement(query)) {

			statement.setString(1,username);

			try(ResultSet resultSet=statement.executeQuery()) {
				if(resultSet.next()) {
					Account account=new Account();
					account.setId(resultSet.getLong("ID"));
					account.setUsername(resultSet.getString("USERNAME"));
					account.setEmail(resultSet.getString("EMAIL"));
					return account;
				}
			}

		} catch(SQLException e) {
			System.out.println("Exception "+e.getMessage());
		}

		return null;
	}

	// Deletes an account by username.
	@Override
	public boolean deleteAccountByUsername(String username) {

		Connection connection=null;
		PreparedStatement statement=null;

		String query="DELETE FROM account WHERE USERNAME=?";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setString(1,username);

			int rowsAffected=statement.executeUpdate();
			return rowsAffected>0;

		} catch(SQLException e) {
			System.out.println("Exception "+e.getMessage());

		} finally {
			try {
				if(statement!=null) {
					statement.close();
				}

				if(connection!=null) {
					connection.close();
				}

			} catch(SQLException e) {
				System.out.println("Exception "+e.getMessage());
			}
		}

		return false;
	}

	// Updates password only when username and email match the same account.
	@Override
	public boolean resetPassword(String username,String email,String newPassword) {

		Connection connection=null;
		PreparedStatement statement=null;

		String query="UPDATE account SET PASSWORD=? WHERE USERNAME=? AND EMAIL=?";

		try {
			connection=dataSource.getConnection();
			statement=connection.prepareStatement(query);

			statement.setString(1,newPassword);
			statement.setString(2,username);
			statement.setString(3,email);

			int rowsAffected=statement.executeUpdate();
			return rowsAffected>0;

		} catch(SQLException e) {
			System.out.println("Exception "+e.getMessage());

		} finally {
			try {
				if(statement!=null) {
					statement.close();
				}

				if(connection!=null) {
					connection.close();
				}

			} catch(SQLException e) {
				System.out.println("Exception "+e.getMessage());
			}
		}

		return false;
	}
}
