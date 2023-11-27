package com.pcpartsshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

import com.pcpartsshop.business.Account;
import com.pcpartsshop.business.Customer;
import com.pcpartsshop.data.AccountDB;
import com.pcpartsshop.data.CustomerDB;
import com.pcpartsshop.util.CookieUtil;

@WebServlet("/accountHandler")
public class CustomerController extends HttpServlet {
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
		
		if (requestURI.endsWith("/accountHandler")) {
			action = request.getParameter("action");
			if (checkActiveSession(request, response)) {
				if (action == null || action.equals("")) {
					url = "/account";
					getServletContext().getRequestDispatcher(url).forward(request, response);
					return;
				}
				if (action.equals("update")) {
					url = updateAccount(request, response);
				} else if (action.equals("signout")) {
					url = signoutAccount(request, response);
				} else {
					url = "/error_pages/error_404.jsp";
				}
			} else {
				if (action == null || action.equals("")) {
					url = "/login";
					getServletContext().getRequestDispatcher(url).forward(request, response);
					return;
				}
				if (action.equals("login")) {
					url = loginAccount(request, response);
				} else if (action.equals("signup")) {
					url = signupAccount(request, response);
				} else {
					url = "/error_pages/error_404.jsp";
				}
			}
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
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
	
	private String loginAccount(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String message = null;
		String url = null;
		
		if (AccountDB.usernameExists(username)) {
			Account temp = AccountDB.selectAccount(username);
			if (password.equals(temp.getPassword())) {
				Long customerID = temp.getCustomer().getCustomerID();
				Customer customer = CustomerDB.selectCustomerByID(customerID);
				
				request.setAttribute("customer", customer);
				session.setAttribute("customer", customer);
				
				Cookie customerIDCookie = new Cookie("customerIDCookie", customerID.toString());
				customerIDCookie.setMaxAge(60 * 60 * 24 * 30);
				customerIDCookie.setPath("/");
				response.addCookie(customerIDCookie);
				
				message = "";
				url = "/account";
			} else {
				message = "Wrong password.";
				url = "/login";
			}
		} else {
			message = "Account does not exist.";
			url = "/login";
		}
		request.setAttribute("message", message);
		return url;
	}
	
	private String signupAccount(HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String message = null;
		String url = null;
		
		if (AccountDB.usernameExists(username)) {
			message = "Account already exists.<br>Please enter a different username.";
			url = "/signup";
		} else {
			Account account = new Account(username, password);
			Customer customer = new Customer();

			account.setCustomer(customer);
			
			CustomerDB.insert(customer);
			AccountDB.insert(account);
			
			message = "Account successfully created!<br>Please <a href='login'>log in</a> with the new account!";
			url = "/signup";
		}
		request.setAttribute("message", message);
		return url;
	}
	
	private String signoutAccount(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		Customer customer = (Customer) session.getAttribute("customer");
		String url = null;
		
		if (customer != null) {
			session.removeAttribute("customer");
			session.invalidate();
			
			Cookie[] cookies = request.getCookies();
			Long customerID = Long.parseLong(CookieUtil.getCookieValue(cookies, "customerIDCookie"));
			Cookie customerIDCookie = new Cookie("customerIDCookie", customerID.toString());
			customerIDCookie.setMaxAge(0);
			customerIDCookie.setPath("/");
			response.addCookie(customerIDCookie);
			
			url = "/login";
		}
		return url;
	}
	
	private String updateAccount(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String message = null;
		
		Customer customer = (Customer) session.getAttribute("customer");
		
		String firstName = request.getParameter("firstName");
		if (!firstName.equals(customer.getFirstName())) {
			customer.setFirstName(firstName);
		}
		
		String lastName = request.getParameter("lastName");
		if (!lastName.equals(customer.getLastName())) {
			customer.setLastName(lastName);
		}
		
		String dateString = request.getParameter("birthDate");
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthDate = dateFormat.parse(dateString);
			
			customer.setBirthDate(birthDate);
		} catch (ParseException ex) {
			message = "Invalid date format.";
		}
		
		String email = request.getParameter("email");
		if (!email.equals("") && !email.equals(customer.getEmail())) {
			if (CustomerDB.emailExists(email)) {
				message = "Email already exists.";
			} else {
				customer.setEmail(email);
			}
		}
		
		String country = request.getParameter("country");
		if (!country.equals(customer.getCountry())) {
			customer.setCountry(country);
		}

		String city = request.getParameter("city");
		if (!city.equals(customer.getCity())) {
			customer.setCity(city);
		}
		
		String address = request.getParameter("address");
		if (!address.equals(customer.getAddress())) {
			customer.setAddress(address);
		}
		
		String cardNumber = request.getParameter("cardNumber");
		if (!cardNumber.equals("") && !cardNumber.equals(customer.getCardNumber())) {
			if (CustomerDB.cardNumberExists(cardNumber)) {
				message = "Card Number already exists.";
			} else {
				customer.setCardNumber(cardNumber);
			}
		}
		
		String cardType = request.getParameter("cardType");
		if (!cardType.equals("") && !cardType.equals(customer.getCardType())) {
			customer.setCardType(cardType);
		}
		
		String cardExpire = request.getParameter("cardExpire");
		if (!cardExpire.equals(customer.getCardExpire())) {
			customer.setCardExpire(cardExpire);
		}
		
		if (message == null) {
			message = "Update successful!";
			CustomerDB.update(customer);
		}
		request.setAttribute("message", message);
		return "/account";
	}
}
