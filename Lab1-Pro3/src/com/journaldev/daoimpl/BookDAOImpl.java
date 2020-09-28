package com.journaldev.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.journaldev.dao.*;
import com.journaldev.model.Account;
import com.journaldev.model.Book;

public class BookDAOImpl implements BookDAO{
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/lab1-pro3?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "kmrdeveloper315";
	
	private final String GET_ALL_BOOKS = "select * from tbl_book";
	private final String GET_BOOKS_BY_NAME = "select * from tbl_book where name like ?";
	private final String GET_BOOK_BY_ID = "select * from tbl_book where id = ?";
	
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
	public List<Book> getAllBook() {
		ArrayList<Book> list = new ArrayList<Book>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_BOOKS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				Book book = new Book(id, name, author);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Book> getBooksByName(String name) {
		ArrayList<Book> list = new ArrayList<Book>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOKS_BY_NAME);) {
			System.out.println(preparedStatement);
			preparedStatement.setString(1, "%" + name + "%");
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String _name = rs.getString("name");
				String author = rs.getString("author");
				Book book = new Book(id, _name, author);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Book getBookById(int id) {
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOK_BY_ID);) {
			System.out.println(preparedStatement);
			preparedStatement.setInt(1, id);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int _id = rs.getInt("id");
				String _name = rs.getString("name");
				String author = rs.getString("author");
				Book book = new Book(_id, _name, author);
				return book;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
