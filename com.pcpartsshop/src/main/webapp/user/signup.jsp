<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>Create an Account</title>
	<link rel="stylesheet" href="main.css" type="text/css">
</head>
<c:import url="/includes/header.jsp" />
	<div class="account-container">
		<div class="card">
			<h1>SIGN UP</h1>
			<p class="warning-text">${message}</p>
			
			<form action="accountHandler" method="POST">
				<input type="hidden" name="action" value="signup">
				
				<label for="username">Username:</label>
				<input required class="signup-info" type="text" placeholder="Enter username" name="username"><br>
				
				<label for="password">Password:</label>
				<input required class="signup-info" type="password" placeholder="Enter password" name="password"><br>
				
				<input class="form-button" type="submit" value="Sign up">
			</form>
			<p>Already have an account? <a href="login">Log in here!</a></p>
		</div>
	</div>
<c:import url="/includes/footer.jsp" />
</html>