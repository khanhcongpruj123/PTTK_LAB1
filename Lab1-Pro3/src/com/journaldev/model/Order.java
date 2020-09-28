package com.journaldev.model;

public class Order {
	private Customer customer;
	private Book book;
	private Address address;
	
	public Order(Customer customer, Book book, Address address) {
		super();
		this.customer = customer;
		this.book = book;
		this.address = address;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
