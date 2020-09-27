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
import com.journaldev.model.Account;
import com.journaldev.model.Customer;
import com.journaldev.model.Customer.Sex;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private AccountDAO accountDAO = new AccountDAOImpl();
	private CustomerDAO customerDAO = new CustomerDAOImpl(accountDAO);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("register.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name =request.getParameter("name");
		String sexString = request.getParameter("sex");
		String address = request.getParameter("address");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		Customer.Sex sex = Sex.MALE;
		try {
			int sexInt = Integer.parseInt(sexString);
			if (sexInt == 1) sex = Sex.FEMALE;
			else sex = Sex.MALE;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Account account = new Account(username, password);
		Customer customer = new Customer(name, sex, address, account);
		
		boolean res = customerDAO.insertCustomer(customer);
		if (res) response.sendRedirect("home.jsp");
		
	}

}
