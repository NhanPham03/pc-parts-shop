<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>Oops!</title>
	<link rel="stylesheet" href="main.css" type="text/css">
	<script src="scripts/go-back.js"></script>
</head>
<c:import url="/includes/header.jsp" />
	<div class="container">
		<h1>Java Error</h1>
		<p>An unexpected error occurred!</p>
		
		<p>To continue, please click the Back button.</p>
		<button class="form-button" onclick="goBack()">Back</button>
	</div>
<c:import url="/includes/footer.jsp" />
</html>