package com.journaldev.dao;

import com.journaldev.model.Account;
import com.journaldev.model.Customer;

public interface CustomerDAO {
	Customer getCustomerByAccount(Account acc);
	boolean insertCustomer(Customer customer);
}
