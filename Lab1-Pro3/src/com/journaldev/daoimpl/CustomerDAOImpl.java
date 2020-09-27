package com.journaldev.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.journaldev.dao.AccountDAO;
import com.journaldev.dao.CustomerDAO;
import com.journaldev.model.Account;
import com.journaldev.model.Customer;
import com.journaldev.model.Customer.Sex;

public class CustomerDAOImpl implements CustomerDAO{
	
	private AccountDAO accountDAO;

	private String jdbcURL = "jdbc:mysql://localhost:3306/lab1-pro3?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "kmrdeveloper315";
	
	private final String SELECT_CUSTOMER_BY_ACCOUNT_ID = "select * from tbl_customer where account_id = ?";
	private final String INSERT_CUSTOMER_SQL = "INSERT INTO tbl_customer(id, name, sex, address, account_id) VALUES (?, ?, ?, ?, ?);";
	
	
	
	public CustomerDAOImpl(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

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
	public Customer getCustomerByAccount(Account acc) {
		Customer customer = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ACCOUNT_ID);) {
			preparedStatement.setInt(1, acc.getId());
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int _id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				Customer.Sex sex = Customer.Sex.MALE;
				int sexInt = rs.getInt("sex");
				if (sexInt == 1) {
					sex = Customer.Sex.FEMALE;
				} else {
					sex = Customer.Sex.MALE;
				}
				customer = new Customer(name, sex, address, acc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public boolean insertCustomer(Customer customer) {
		boolean succesed = accountDAO.insertAccount(customer.getAccount());
		Account acc = accountDAO.getAccountByUsernameAndPassword(customer.getAccount().getUsername(), customer.getAccount().getPassword());
		if (succesed) {
			System.out.println(INSERT_CUSTOMER_SQL);
			// try-with-resource statement will auto close the connection.
			try (Connection connection = getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
				
				int sexInt = 0;
				if (customer.getSex() == Sex.FEMALE) sexInt = 1;
				
				preparedStatement.setInt(1, 0);
				preparedStatement.setString(2, customer.getName());
				preparedStatement.setInt(3, sexInt);
				preparedStatement.setString(4, customer.getAddress());
				preparedStatement.setInt(5, acc.getId());
				System.out.println(preparedStatement);
				preparedStatement.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

}
