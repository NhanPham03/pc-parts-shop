<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>Your Account</title>
	<link rel="stylesheet" href="main.css" type="text/css">
	<script src="https://cdn.jsdelivr.net/npm/cleave.js@1.6.0/dist/cleave.min.js"></script>
	<script src="scripts/account-card-number.js"></script>
</head>
<c:import url="/includes/header.jsp" />
	<div style="width: 60%;" class="account-container">
		<div class="card">
			<h1>ACCOUNT INFORMATION</h1>
			<p class="warning-text">${message}</p>
			
			<form action="accountHandler" method="POST">
				<input type="hidden" name="action" value="update">
				
				<div class="form-items">
					<div class="form-left">
						<label for="firstName">First Name:</label>
						<input class="account-info" type="text" placeholder="e.g. John" name="firstName" value="${customer.firstName}"><br>
						
						<label for="birthDate">Date of Birth:</label>
						<input class="account-info" type="date" name="birthDate" value="${customer.formattedBirthDate}"><br>
						
						<label for="country">Country of Origin:</label>
						<input class="account-info" type="text" placeholder="e.g. United States" name="country" value="${customer.country}"><br>
						
						<label for="address">Address:</label>
						<input class="account-info" type="text" placeholder="e.g. 123 Main Street" name="address" value="${customer.address}"><br>
					</div>
					<div class="form-right">
						<label for="lastName">Last Name:</label>
						<input class="account-info" type="text" placeholder="e.g. Doe" name="lastName" value="${customer.lastName}"><br>
						
						<label for="email">Email:</label>
						<input class="account-info" type="email" placeholder="example@email.com" name="email" value="${customer.email}"><br>
						
						<label for="city">City:</label>
						<input class="account-info" type="text" placeholder="e.g. Anytown" name="city" value="${customer.city}"><br>
					</div>
				</div>
				<div style="margin-bottom: 0.5em;" class="separator"></div>
				<div class="form-items">
					<div class="form-left">
						<label for="cardNumber">Card Number:</label>
						<input class="account-info" type="text" placeholder="XXXX-XXXX-XXXX-XXXX" name="cardNumber" id="creditCardInput" pattern="[0-9-]*" value="${customer.cardNumber}"><br>
						
						<label for="cardExpire">Expiration Date:</label>
						<input class="account-info" type="month" name="cardExpire" value="${customer.cardExpire}"><br>
					</div>
					<div class="form-right">
						<label for="cardType">Card Type:</label>
						<select class="account-info" name="cardType">
							<option value="">--Select a card type--</option>
							<option value="Visa" ${customer.cardType=='Visa'?'selected':''}>Visa</option>
							<option value="MasterCard" ${customer.cardType=='MasterCard'?'selected':''}>MasterCard</option>
							<option value="American Express" ${customer.cardType=='American Express'?'selected':''}>American Express</option>
						</select>
					</div>
				</div>
				<input class="form-button" type="submit" value="Update">
			</form>
			<form action="accountHandler" method="POST">
				<input type="hidden" name="action" value="signout">
				<input class="form-button" style="margin-top: 10px; color: red;" type="submit" value="Sign out">
			</form>
		</div>
	</div>
<c:import url="/includes/footer.jsp" />
</html>