package com.pcpartsshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.pcpartsshop.business.*;
import com.pcpartsshop.data.*;
import com.pcpartsshop.util.CookieUtil;
import com.pcpartsshop.util.SQLUtil;

@WebServlet("/invoiceHandler")
public class InvoiceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String action = null;
		String url = null;
		
		if (requestURI.endsWith("/invoiceHandler")) {
			action = request.getParameter("action");
			if (checkActiveSession(request, response)) {
				if (action == null || action.equals("")) {
					url = "/cartHandler";
					getServletContext().getRequestDispatcher(url).forward(request, response);
					return;
				}
				if (action.equals("checkout")) {
					url = checkout(request, response);
				} else {
					url = "/error_pages/error_404.jsp";
				}
			} else {
				url = "/login";
			}
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	private Cart getCart(Customer customer) {
		Cart cart = CartDB.selectCartByCustomerID(customer.getCustomerID());
		
		return cart;
	}
	
	private void clearCart(Customer customer) {
		Cart cart = getCart(customer);
		
		cart.setActive(false);
		CartDB.update(cart);
	}
	
	private boolean checkActiveSession(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");

		if (customer != null) {
			return true;
		} else {
			try {
				Cookie[] cookies = request.getCookies();
				Long customerID = Long.parseLong(CookieUtil.getCookieValue(cookies, "customerIDCookie"));
				
				customer = CustomerDB.selectCustomerByID(customerID);
				if (customer != null) {
					session.setAttribute("customer", customer);
					return true;
				}
			} catch (NumberFormatException ex) {
				System.out.println(ex);
			}
		}
		return false;
	}
	
	private String checkout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String url = null;
		
		Cart cart = getCart(customer);
		Invoice invoice = new Invoice(customer, cart);
		InvoiceDB.insert(invoice);
		
		String invoiceResult = SQLUtil.getInvoice(invoice);
		request.setAttribute("invoiceResult", invoiceResult);
		
		clearCart(customer);
		url = "/invoice";
		
		return url;
	}
}
