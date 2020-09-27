package com.journaldev.dao;

import com.journaldev.model.Account;

public interface AccountDAO {
	Account getAccountByUsernameAndPassword(String username, String password);
	boolean insertAccount(Account account);
}
