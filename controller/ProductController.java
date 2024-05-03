package com.pcpartsshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.pcpartsshop.business.Product;
import com.pcpartsshop.data.ProductDB;


@WebServlet("/productHandler")
public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String url = null;

		if (requestURI.endsWith("/productHandler")) {
			url = displayProduct(request, response);
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String displayProduct(HttpServletRequest request, HttpServletResponse response) {
		String productID = request.getParameter("productID");
		String url = null;

		Product product = ProductDB.selectProductByID(productID);

		if (product != null) {
			request.setAttribute("product", product);
			request.setAttribute("type", product.getType().toLowerCase());
			url = "/product";
		} else {
			url = "/error_pages/error_404.jsp";
		}
		return url;
	}
}
