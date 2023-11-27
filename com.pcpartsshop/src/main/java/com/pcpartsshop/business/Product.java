package com.pcpartsshop.business;

import jakarta.persistence.*;

@Entity 
public class Product {
	@Id
	private String productID;

	private String name;
	private String description;
	private String type;
	private float price;

	// Constructor
	public Product() {
		this.productID = "";
		this.name = "";
		this.description = "";
		this.type = "";
		this.price = 0;
	}

	public Product(String productID, String name, String description, String type, float price) {
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.type = type;
		this.price = price;
	}

	// Product ID
	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductID() {
		return this.productID;
	}

	// Name
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	// Description
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	// Type
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}
	
	// Price
	public void setPrice(float price) {
		this.price = price;
	}

	public float getPrice() {
		return this.price;
	}
}
