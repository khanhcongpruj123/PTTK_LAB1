package com.journaldev.model;

public class Customer {
	
	private int id;
	private String name;
	private Sex sex;
	private String address;
	private Account account;
	
	
	public Customer(int id, String name, Sex sex, String address, Account account) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.account = account;
	}
	
	public Customer(String name, Sex sex, String address, Account account) {
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.account = account;
	}

	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Sex getSex() {
		return sex;
	}



	public void setSex(Sex sex) {
		this.sex = sex;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public Account getAccount() {
		return account;
	}



	public void setAccount(Account account) {
		this.account = account;
	}



	public enum Sex {
		MALE,
		FEMALE
	}

}
