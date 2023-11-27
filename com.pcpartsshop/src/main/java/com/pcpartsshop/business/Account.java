package com.pcpartsshop.business;

import jakarta.persistence.*;

@Entity
public class Account {
	@Id
	private String username;

	private String password;

	@OneToOne
	@JoinColumn(name = "customerID")
	private Customer customer;

	// Constructor
	public Account() {
	}

	public Account(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// Username
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return this.username;
	}

	// Password
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	// User
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return this.customer;
	}
}
