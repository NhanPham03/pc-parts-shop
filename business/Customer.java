package com.pcpartsshop.business;

import java.util.Date;
import java.text.SimpleDateFormat;

import jakarta.persistence.*;

@Entity 
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerID;

	private String firstName;
	private String lastName;
	private Date birthDate;
	private String email;
	private String country;
	private String city;
	private String address;

	private String cardNumber;
	private String cardType;
	
	@Transient
	private String cardExpire;
	
	private int expireMonth;
	private int expireYear;

	@Transient
	private String formattedBirthDate;
	
	@OneToOne(mappedBy = "customer")
	private Account account;
	
	// Constructor
	public Customer() {
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.country = "";
		this.city = "";
		this.address = "";

		this.cardNumber = "";
		this.cardType = "";
		this.cardExpire = "";

		this.formattedBirthDate = "";
	}

	public Customer(String firstName, String lastName, Date birthDate, String email, String country, String city,
			String address, String cardNumber, String cardType, String cardExpire) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.country = country;
		this.city = city;
		this.address = address;

		this.cardNumber = cardNumber;
		this.cardType = cardType;
		
		String[] parts = cardExpire.split("-");
		this.expireMonth = Integer.parseInt(parts[0]);
		this.expireYear = Integer.parseInt(parts[1]);
		
		this.birthDate = birthDate;
		this.formattedBirthDate = formatDate(birthDate);
	}

	// Method
	private String formatDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}
	
	// User ID
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}

	public long getCustomerID() {
		return this.customerID;
	}

	// First Name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	// Last Name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	// Email
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	// Birth Date & Formatted Birth Date
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
		this.formattedBirthDate = formatDate(birthDate);
	}

	public Date getBirthDate() {
		return this.birthDate;
	}

	public void setFormattedBirthDate(String formattedBirthDate) {
		this.formattedBirthDate = formattedBirthDate;
	}
	
	public String getFormattedBirthDate() {
		return this.formattedBirthDate;
	}

	// Country
	public void setCountry(String country) {
		this.country = country;
	}

	public String getCountry() {
		return this.country;
	}

	// City
	public void setCity(String city) {
		this.city = city;
	}

	public String getCity() {
		return this.city;
	}

	// Address
	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return this.address;
	}

	// Card Number
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	// Card Type
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardType() {
		return this.cardType;
	}

	// Expiration Month & Year
	public void setExpireMonth(int expireMonth) {
		this.expireMonth = expireMonth;
	}
	
	public int getExpireMonth() {
		return this.expireMonth;
	}

	public void setExpireYear(int expireYear) {
		this.expireYear = expireYear;
	}
	
	public int getExpireYear() {
		return this.expireYear;
	}
	
	public void setCardExpire(String cardExpire) {
		this.cardExpire = cardExpire;
		
		String[] parts = cardExpire.split("-");
		this.expireMonth = Integer.parseInt(parts[0]);
		this.expireYear = Integer.parseInt(parts[1]);
	}
	
	public String getCardExpire() {
		return this.cardExpire;
	}
	
	// Account
	public void setAccount(Account account) {
		this.account = account;
	}

	public Account getAccount() {
		return this.account;
	}
}
