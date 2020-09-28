package com.journaldev.dao;

import com.journaldev.model.Order;

public interface OrderDAO {
	boolean insertOrder(Order order);
}
