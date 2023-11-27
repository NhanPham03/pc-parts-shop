package com.pcpartsshop.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.pcpartsshop.business.*;
import com.pcpartsshop.data.*;
import com.pcpartsshop.util.CookieUtil;
import com.pcpartsshop.util.SQLUtil;

@WebServlet("/cartHandler")
public class CartController extends HttpServlet {
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
		
		if (requestURI.endsWith("/cartHandler")) {
			action = request.getParameter("action");
			if (checkActiveSession(request, response)) {
				if (action == null || action.equals("")) {
					url = displayCart(request, response);
				} else if (action.equals("new")) {
					url = newItem(request, response);
				} else if (action.equals("add")) {
					url = addItem(request, response);
				} else if (action.equals("remove")) {
					url = removeItem(request, response);
				} else if (action.equals("delete")) {
					url = deleteItem(request, response);
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
		Cart cart = null;
		
		cart = CartDB.selectCartByCustomerID(customer.getCustomerID());
		if (cart == null) {
			cart = new Cart();
			cart.setCustomer(customer);
			cart.setActive(true);
			CartDB.insert(cart);
		}
		return cart;
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
	
	private String displayCart(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String url = null;
		
		Cart cart = getCart(customer);
		
		int itemCount = CartDB.getItemCount(cart);
		request.setAttribute("itemCount", itemCount);
		
		String cartResult = SQLUtil.getCustomerCart(cart.getItems());
		request.setAttribute("cartResult", cartResult);
		url = "/cart";
		
		return url;
	}
	
	private String newItem(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		boolean itemExists = false;
		String url = null;
		
		Cart cart = getCart(customer);
		String productID = request.getParameter("productID");
		Product product = ProductDB.selectProductByID(productID);
		
		List<Item> items = cart.getItems();
		for (Item item : items) {
			Product temp = item.getProduct();
			if (temp.getProductID().equals(productID)) {
				itemExists = true;
				item.increase();
				ItemDB.update(item);
				break;
			}
		}
		if (!itemExists) {
			Item item = new Item();
			
			item.setProduct(product);
			item.setQuantity(1);
			ItemDB.insert(item);
			
			cart.addItem(item);
			CartDB.update(cart);
		}
		request.setAttribute("product", product);
		request.setAttribute("type", product.getType().toLowerCase());
		request.setAttribute("script", "alert('Product added to cart!')");
		url = "/product";
		
		return url;
	}
	
	private String addItem(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String url = null;
		
		Cart cart = getCart(customer);
		String productID = request.getParameter("productID");
		
		List<Item> items = cart.getItems();
		for (Item item : items) {
			Product temp = item.getProduct();
			if (temp.getProductID().equals(productID)) {
				item.increase();
				ItemDB.update(item);
				break;
			}
		}
		url = displayCart(request, response);
		
		return url;
	}
	
	private String removeItem(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String url = null;
		
		Cart cart = getCart(customer);
		String productID = request.getParameter("productID");
		
		List<Item> items = cart.getItems();
		for (Item item : items) {
			Product temp = item.getProduct();
			if (temp.getProductID().equals(productID)) {
				if (item.getQuantity() > 1) {
					item.decrease();
					ItemDB.update(item);
				} else {
					cart.removeItem(item);
					CartDB.update(cart);
					ItemDB.delete(item);
				}
				break;
			}
		}
		url = displayCart(request, response);
		
		return url;
	}
	
	private String deleteItem(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String url = null;
		
		Cart cart = getCart(customer);
		String productID = request.getParameter("productID");
		
		List<Item> items = cart.getItems();
		for (Item item : items) {
			Product temp = item.getProduct();
			if (temp.getProductID().equals(productID)) {
				cart.removeItem(item);
				CartDB.update(cart);
				ItemDB.delete(item);
				break;
			}
		}
		url = displayCart(request, response);
		
		return url;
	}
}
