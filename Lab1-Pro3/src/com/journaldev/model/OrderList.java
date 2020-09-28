package com.journaldev.model;

import java.util.List;

public class OrderList {
	
	private Customer customer;
	private List<Order> listOrder;
	
	public OrderList(Customer customer, List<Order> listOrder) {
		super();
		this.customer = customer;
		this.listOrder = listOrder;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Order> getListOrder() {
		return listOrder;
	}

	public void setListOrder(List<Order> listOrder) {
		this.listOrder = listOrder;
	}
	
	
}
