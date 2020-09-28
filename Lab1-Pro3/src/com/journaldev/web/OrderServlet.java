package com.journaldev.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.journaldev.dao.BookDAO;
import com.journaldev.dao.OrderDAO;
import com.journaldev.daoimpl.BookDAOImpl;
import com.journaldev.daoimpl.OrderDAOImpl;
import com.journaldev.model.*;

/**
 * Servlet implementation class OrderServlet
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookDAO bookDAO = new BookDAOImpl();
	private OrderDAO orderDAO = new OrderDAOImpl();
	
	Book book = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookIdStr = request.getParameter("id");
		int bookId = Integer.parseInt(bookIdStr);
		book = bookDAO.getBookById(bookId);
		System.out.println("Order: " + book.getId());
		
		response.sendRedirect(request.getContextPath() + "/order.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (book == null) {
			response.sendRedirect(request.getContextPath()+ "/login");
		} else {
			String city = request.getParameter("city");
			String commune = request.getParameter("commune");
			String district = request.getParameter("district");
			String street = request.getParameter("street");
			String number = request.getParameter("number");
			
			Customer customer = LoginServlet.customer;
			
			if(customer == null) {
				System.out.println("Order that bai!");
				response.sendRedirect(request.getContextPath() + "/login");
				return;
			}
			
			Address address = new Address(city, district, commune, street, number);
			Order order = new Order(customer, book, address);
			boolean succed = orderDAO.insertOrder(order);
			if (succed) {
				System.out.println("Order thanh cong!");
				response.sendRedirect(request.getContextPath() + "/success.jsp");
			}
			else System.out.println("Order that bai!");
		}
	}

}
