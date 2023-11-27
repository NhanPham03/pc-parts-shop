package com.pcpartsshop.business;

import jakarta.persistence.*;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "productID")
	private Product product;
	
	private int quantity;

	// Constructor
	public Item() {
	}
	
	public Item(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	// Method
	public void increase() {
		this.quantity += 1;
	}
	
	public void decrease() {
		if (this.quantity > 0) {
			this.quantity -= 1;
		} else {
			this.quantity = 0;
		}
	}
	
	// ID
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return this.id;
	}
	
	// Product
	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return this.product;
	}

	// Quantity
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return this.quantity;
	}
}
