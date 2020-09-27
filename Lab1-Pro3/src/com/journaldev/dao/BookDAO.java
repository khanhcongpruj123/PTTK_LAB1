package com.journaldev.dao;

import java.util.List;

import com.journaldev.model.Book;

public interface BookDAO {
	List<Book> getAllBook();
	List<Book> getBooksByName(String name);
}
