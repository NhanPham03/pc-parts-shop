<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>Your Cart</title>
	<link rel="stylesheet" href="main.css" type="text/css">
</head>
<c:import url="/includes/header.jsp" />
	<div class="container">
		<h2 style="margin: 10px 20px;">Showing ${itemCount} items in cart.</h2>
		<c:out value="${cartResult}" escapeXml="false" />
	</div>
<c:import url="/includes/footer.jsp" />
</html>