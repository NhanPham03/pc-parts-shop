package com.pcpartsshop.util;

import java.util.List;

import com.pcpartsshop.business.*;

public class SQLUtil {
	public static String getProductCatalog(List<Product> productList) {
		StringBuilder htmlResult = new StringBuilder();
		int productCount = productList.size();
		int maxLength = 35;
		
		if (productCount > 0) {
			for (int row = 1; row <= (int) Math.ceil(productCount / 3.0); row++) {
				htmlResult.append("<div class='catalog-row'>");
				for (int col = (row - 1) * 3 + 1; col <= (row * 3); col++) {
					Product product = null;
					try {
						product = productList.get(col - 1);
					} catch (Exception ex) {
						break;
					}
					htmlResult.append("<div class='catalog-column'>");
					htmlResult.append("<div class='card'>");
					
					htmlResult.append("<form action='productHandler' method='GET'>");
					String inputID = String.format("<input type='hidden' name='productID' value='%s'>", product.getProductID());
					htmlResult.append(inputID);
					
					htmlResult.append("<button class='catalog-button' type='submit'>");
					String img = String.format("<img src='images/preview_img/%1$s/%2$s.jpg' alt='%2$s.jpg'>", product.getType().toLowerCase(), product.getProductID());
					htmlResult.append(img);
					htmlResult.append("</button>");
					
					htmlResult.append("<button class='catalog-button' type='submit'>");
					String name = product.getName().length() > maxLength ? product.getName().substring(0, maxLength) + "..." : product.getName();
					htmlResult.append("<h3 class='catalog-info'>" + name + "</h3>");
					htmlResult.append("</button>");
					
					htmlResult.append("<p class='catalog-info'>");
					String price = String.format("$%.2f", product.getPrice());
					htmlResult.append("<b>" + price + "</b>");
					htmlResult.append("</p>");
					
					htmlResult.append("</form>");
					htmlResult.append("</div>");
					htmlResult.append("</div>");
				}
				htmlResult.append("</div>");
			}
		} else {
			htmlResult.append("<p style='color: gray; text-align-last: center; margin-top: 3em;'>No results found...</p>");
		}
		return htmlResult.toString();
	}
	
	public static String getCustomerCart(List<Item> itemList) {
		StringBuilder htmlResult = new StringBuilder();
		float totalPrice = 0;
		int itemCount = itemList.size();
		
		if (itemCount > 0) {
			htmlResult.append("<div id='cart-header' class='card'>");
			htmlResult.append("<div class='item-container'>");
			
			htmlResult.append("<div class='item-image'><h3>Preview</h3></div>");
			htmlResult.append("<div class='item-name'><h3>Product Name</h3></div>");
			htmlResult.append("<div class='item-quantity'><h3>Quantity</h3></div>");
			htmlResult.append("<div class='item-price'><h3>Price</h3></div>");
			
			htmlResult.append("</div>");
			htmlResult.append("<div class='separator'></div>");
			htmlResult.append("</div>");
			
			for (int i = 0; i < itemCount; i++) {
				Item item = itemList.get(i);
				Product product = item.getProduct();
				
				htmlResult.append("<form action='cartHandler' method='POST'>");
				String inputID = String.format("<input type='hidden' name='productID' value='%s'>", product.getProductID());
				htmlResult.append(inputID);
				htmlResult.append("<button style='float: inline-start; margin: 0 -1px;' class='round-button' type='submit' name='action' value='delete'>x</button>");
				htmlResult.append("</form>");
				
				htmlResult.append("<div class='card'>");
				htmlResult.append("<div class='item-container'>");
				
				htmlResult.append("<div class='item-image'>");
				String img = String.format("<img src='images/preview_img/%1$s/%2$s.jpg' alt='%2$s.jpg'>", product.getType().toLowerCase(), product.getProductID());
				htmlResult.append(img);
				htmlResult.append("</div>");
				
				htmlResult.append("<div class='item-name'>");
				htmlResult.append("<form class='item-form' action='productHandler' method='GET'>");
				htmlResult.append(inputID);
				htmlResult.append("<button class='item-button' type='submit'>");
				htmlResult.append("<h3>" + product.getName() + "</h3>");
				htmlResult.append("</button>");
				htmlResult.append("</form>");
				htmlResult.append("</div>");
				
				htmlResult.append("<div class='item-quantity'>");
				htmlResult.append("<form action='cartHandler' method='POST'>");
				htmlResult.append(inputID);
				htmlResult.append("<button class='round-button' type='submit' name='action' value='add'>+</button>");
				String inputQuantity = String.format("<input style='width: 40px; text-align: end;' type='number' min='0' value='%d'>", item.getQuantity());
				htmlResult.append(inputQuantity);
				htmlResult.append("<button class='round-button' type='submit' name='action' value='remove'>-</button>");
				htmlResult.append("</form>");
				htmlResult.append("</div>");
				
				htmlResult.append("<div class='item-price'>");
				String price = String.format("%.2f", product.getPrice() * item.getQuantity());
				htmlResult.append("<p>$" + price + "</p>");
				htmlResult.append("</div>");
				
				htmlResult.append("</div>");
				htmlResult.append("</div>");
				
				totalPrice += product.getPrice() * item.getQuantity();
			}
			htmlResult.append("<div id='cart-footer' class='card'>");
			htmlResult.append("<div class='separator'></div>");
			htmlResult.append(String.format("<h3>Total price: $%.2f</h3>", totalPrice));
			htmlResult.append("<form action='invoiceHandler' method='POST'>");
			htmlResult.append("<input type='hidden' name='action' value='checkout'>");
			htmlResult.append("<input class='form-button' type='submit' value='Go to checkout'>");
			htmlResult.append("</form>");
			htmlResult.append("</div>");
		} else {
			htmlResult.append("<p style='color: gray; text-align-last: center; margin-top: 3em;'>You haven't bought anything...</p>");
		}
		return htmlResult.toString();
	}
	
	public static String getInvoice(Invoice invoice) {
		StringBuilder htmlResult = new StringBuilder();
		Customer customer = invoice.getCustomer();
		
		String name = String.format("%1$s %2$s", customer.getFirstName(), customer.getLastName());
		htmlResult.append("<div class='invoice-container'>");
		htmlResult.append("<p><b>Name: </b>" + name + "</p>");
		htmlResult.append("<p><b>Email: </b>" + customer.getEmail() + "</p>");
		htmlResult.append("<p><b>Address: </b>" + customer.getAddress() + "</p>");
		htmlResult.append("</div>");
		htmlResult.append("<div class='separator'></div>");
		
		htmlResult.append("<div class='invoice-container'>");
		htmlResult.append("<table>");
		htmlResult.append("<tr>");
		htmlResult.append("<th>Product Name</th>");
		htmlResult.append("<th>Quantity</th>");
		htmlResult.append("<th>Price</th>");
		htmlResult.append("</tr>");
		
		List<Item> items = invoice.getCart().getItems();
		for (Item item : items) {
			Product product = item.getProduct();
			
			htmlResult.append("<tr>");
			htmlResult.append("<td>" + product.getName() + "</td>");
			htmlResult.append("<td>" + item.getQuantity() + "</td>");
			String total = String.format("%.2f", product.getPrice() * item.getQuantity());
			htmlResult.append("<td>$" + total + "</td>");
			htmlResult.append("</tr>");
		}
		htmlResult.append("</table>");
		htmlResult.append("</div>");
		
		return htmlResult.toString();
	}
}
