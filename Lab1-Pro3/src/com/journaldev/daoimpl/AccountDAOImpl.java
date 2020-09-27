package com.journaldev.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.journaldev.dao.*;
import com.journaldev.model.Account;

public class AccountDAOImpl implements AccountDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/lab1-pro3?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "kmrdeveloper315";
	
	private final String SELECT_ACCOUNT_BY_USERNAME_AND_PASSWORD = "select * from tbl_account where username = ? and password = ?";
	private final String INSERT_ACCOUNT_SQL = "INSERT INTO tbl_account(id, username, password) VALUES (?, ?, ?);";
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.println("Connection is null? " + (connection == null));
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	public Account getAccountByUsernameAndPassword(String username, String password) {
		Account account = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_BY_USERNAME_AND_PASSWORD);) {
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("username");
				String country = rs.getString("password");
				account = new Account(id, username, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public boolean insertAccount(Account account) {
		System.out.println(INSERT_ACCOUNT_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL)) {
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, account.getUsername());
			preparedStatement.setString(3, account.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
