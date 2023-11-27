package com.pcpartsshop.business;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@OneToOne
	@JoinColumn(name = "customerID")
	private Customer customer;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Item> items = new ArrayList<>();
	
	private boolean active;
	
	// Constructor
	public Cart() {
	}
	
	public Cart(Customer customer) {
		this.customer = customer;
	}
	
	// Method
	public void addItem(Item item) {
		this.items.add(item);
	}

	public void removeItem(Item item) {
		String productID = item.getProduct().getProductID();
		
		for (int i = 0; i < items.size(); i++) {
			Item temp = items.get(i);
			if (temp.getProduct().getProductID().equals(productID)) {
				this.items.remove(i);
				break;
			}
		}
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

	// Items
	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Item> getItems() {
		return this.items;
	}
	
	// Active
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	public void toggleActive() {
		this.active = !this.active;
	}
}
