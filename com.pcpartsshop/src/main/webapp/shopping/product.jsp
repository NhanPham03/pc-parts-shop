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
		<div class="card">
			<div class="product-container">
				<div class="product-header">
					<h2>
						<img src="images/preview_img/${type}/${product.productID}.jpg" alt="${product.productID}.jpg">
						${product.name}<br>
					</h2>
					<h3>$${product.price}</h3>
					<form action="cartHandler" method="GET">
						<input type="hidden" name="action" value="new">
						<input type="hidden" name="productID" value="${product.productID}">
						<input class="product-button" type="submit" value="Add to cart">
					</form>
				</div>
				<div class="separator"></div>
				<div class="product-body">
					<h2>Description</h2>
					<p>${product.description}</p>
				</div>
			</div>
		</div>
	</div>
	<script>${script}</script>
<c:import url="/includes/footer.jsp" />
</html>