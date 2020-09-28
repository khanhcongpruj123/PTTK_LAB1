package com.journaldev.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.journaldev.dao.OrderDAO;
import com.journaldev.model.Order;

public class OrderDAOImpl implements OrderDAO{
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/lab1-pro3?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "kmrdeveloper315";
	
	private final String INSERT_ORDER = "insert into tbl_orders(id, customer_id, book_id, address) values(?, ?, ?, ?)";
	
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
	public boolean insertOrder(Order order) {
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER)) {
			System.out.println(preparedStatement.toString());
			preparedStatement.setInt(1, 0);
			preparedStatement.setInt(2, order.getCustomer().getId());
			preparedStatement.setInt(3, order.getBook().getId());
			preparedStatement.setString(4, order.getAddress().toString());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
