package com.pcpartsshop.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import com.pcpartsshop.data.ProductDB;
import com.pcpartsshop.util.SQLUtil;
import org.apache.commons.text.StringEscapeUtils; // Import Apache Commons Text

@WebServlet("/catalogHandler")
public class CatalogController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final List<String> ALLOWED_PARAMS =
			Arrays.asList("CPU", "GPU", "RAM", "Motherboard", "Storage", "Case", "PSU", "Monitor", "Keyboard", "Mouse", "Headset");

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Validate the params
		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements()) {
			String paramName = parameterNames.nextElement();

			// Validate the parameter name
			if (!ALLOWED_PARAMS.contains(paramName)) {
				throw new ServletException("Invalid parameter: " + paramName);
			}

			String[] paramValues = request.getParameterValues(paramName);

			// Validate the parameter values
			for (String paramValue : paramValues) {
				if (!isValid(paramValue)) {
					throw new ServletException("Invalid parameter value: " + paramValue);
				}
			}
		}


		// Process the parameters...
		String requestURI = request.getRequestURI();
		String action = null;
		String url = null;

		if (requestURI.endsWith("/catalogHandler")) {
			action = request.getParameter("action");
			if (action == null || action.equals("")) {
				url = displayAll(request, response);
			}
			if (action.equals("search")) {
				url = searchCatalog(request, response);
			} else if (action.equals("filter")) {
				url = filterCatalog(request, response);
			} else {
				url = "/error_pages/error_404.jsp";
			}
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	private String displayAll(HttpServletRequest request, HttpServletResponse response) {
		String url = null;

		request.setAttribute("title", "All products");

		int productCount = ProductDB.getTotalCount();
		request.setAttribute("productCount", productCount);

		String catalogResult = SQLUtil.getProductCatalog(ProductDB.selectAllProducts());
		request.setAttribute("catalogResult", catalogResult);
		url = "/catalog";

		return url;
	}

	private String filterCatalog(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String url = null;

		if (type == null || type.equals("")) {
			return displayAll(request, response);
		}
		type = StringEscapeUtils.escapeHtml4(type); // Escape the 'type' parameter
		request.setAttribute("title", "Category: " + type);

		int productCount = ProductDB.getCountOfType(type);
		request.setAttribute("productCount", productCount);

		String catalogResult = SQLUtil.getProductCatalog(ProductDB.filterByType(type));
		request.setAttribute("catalogResult", catalogResult);
		url = "/catalog";

		return url;
	}

	private String searchCatalog(HttpServletRequest request, HttpServletResponse response) {
		String q = request.getParameter("q").trim();
		String url = null;

		if (q == null || q.equals("")) {
			return displayAll(request, response);
		}
		q = StringEscapeUtils.escapeHtml4(q); // Escape the 'q' parameter
		request.setAttribute("title", "You searched for: " + q);

		int productCount = ProductDB.getCountOfName(q);
		request.setAttribute("productCount", productCount);

		String catalogResult = SQLUtil.getProductCatalog(ProductDB.filterByName(q));
		request.setAttribute("catalogResult", catalogResult);
		url = "/catalog";

		return url;
	}

	private boolean isValid(String paramValue) {
		// Check if the value is alphanumeric and does not contain any special characters
		return paramValue.matches("[a-zA-Z0-9]*");
	}

}
