package com.pcpartsshop.business;

import jakarta.persistence.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "customerID")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name = "cartID")
	private Cart cart;
	
	private Date date;
	private float total;
	
	@Transient
	private String formattedDate;
	
	// Constructor
	public Invoice() {
	}
	
	public Invoice(Customer customer, Cart cart) {
		this.customer = customer;
		this.cart = cart;
		
		for (Item item : cart.getItems()) {
			Product product = item.getProduct();
			this.total += product.getPrice() * item.getQuantity();
		}
		
		LocalDate localDate = LocalDate.now();
		Date today = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		this.date = today;
		this.formattedDate = formatDate(today);
	}
	
	// Method
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy");
		return format.format(date);
	}
	
	// ID
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	// Customer
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	// Cart
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	public Cart getCart() {
		return this.cart;
	}
	
	// Date & FormattedDate
	public void setDate(Date date) {
		this.date = date;
		this.formattedDate = formatDate(date);
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public String getFormattedDate() {
		return this.formattedDate;
	}
	
	// Total
	public void setTotal(float total) {
		this.total = total;
	}
	
	public float getTotal() {
		return this.total;
	}
}
