package com.journaldev.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.journaldev.dao.AccountDAO;
import com.journaldev.dao.CustomerDAO;
import com.journaldev.daoimpl.AccountDAOImpl;
import com.journaldev.daoimpl.CustomerDAOImpl;
import com.journaldev.model.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private AccountDAO accountDAO = new AccountDAOImpl();
	private CustomerDAO customerDAO = new CustomerDAOImpl(accountDAO);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("login.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Customer customer = checkLogin(username, password);
		if (customer != null) {
			resp.sendRedirect("home.jsp");
		} else {
			
		}
	}
	
	private Customer checkLogin(String username, String password) {
		Account acc = accountDAO.getAccountByUsernameAndPassword(username, password);
		return customerDAO.getCustomerByAccount(acc);
	}
}
