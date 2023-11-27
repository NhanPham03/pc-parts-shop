<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>PC Parts Shop</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.css">
	<link rel="stylesheet" href="main.css" type="text/css">
	<script src="https://cdn.jsdelivr.net/npm/swiper@11/swiper-bundle.min.js"></script>
	<script src="scripts/product-filter.js"></script>
</head>
<c:import url="/includes/header.jsp" />
	<div class="container">
		<div class="home-row">
			<div class="home-column-left">
				<div class="card">
					<h2>Products</h2>
					<form id="filter" action="catalogHandler" method="GET">
						<input type="hidden" name="action" value="filter">
						<input id="type" type="hidden" name="type" value="">
						<button class="product-filter" type="submit" onclick="submitForm('CPU')"><b>CPU				></b></button>
						<button class="product-filter" type="submit" onclick="submitForm('GPU')"><b>GPU				></b></button>
						<button class="product-filter" type="submit" onclick="submitForm('RAM')"><b>RAM			></b></button>
						<button class="product-filter" type="submit" onclick="submitForm('Motherboard')"><b>Motherboard		></b></button>
						<button class="product-filter" type="submit" onclick="submitForm('Storage')"><b>Storage			></b></button>
						<button class="product-filter" type="submit" onclick="submitForm('Case')"><b>Case			></b></button>
						<button class="product-filter" type="submit" onclick="submitForm('PSU')"><b>PSU				></b></button>
						<button class="product-filter" type="submit" onclick="submitForm('Monitor')"><b>Monitor			></b></button>
						<button class="product-filter" type="submit" onclick="submitForm('Keyboard')"><b>Keyboard		></b></button>
						<button class="product-filter" type="submit" onclick="submitForm('Mouse')"><b>Mouse			></b></button>
						<button class="product-filter" type="submit" onclick="submitForm('Headset')"><b>Headset			></b></button>
					</form>
				</div>
			</div>
			<div class="home-column-main">
				<div class="swiper">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<form action="productHandler" method="GET">
								<input type="hidden" name="productID" value="CPU_008">
								<button class="banner-button" type="submit">
									<img src="images/preview_img/slider/img-1.jpg" alt="Slide 1">
								</button>
							</form>
						</div>
						<div class="swiper-slide">
							<form action="productHandler" method="GET">
								<input type="hidden" name="productID" value="GPU_007">
								<button class="banner-button" type="submit">
									<img src="images/preview_img/slider/img-2.jpg" alt="Slide 2">
								</button>
							</form>
						</div>
						<div class="swiper-slide">
							<form action="productHandler" method="GET">
								<input type="hidden" name="productID" value="MTB_003">
								<button class="banner-button" type="submit">
									<img src="images/preview_img/slider/img-3.jpg" alt="Slide 3">
								</button>
							</form>
						</div>
						<div class="swiper-slide">
							<form action="productHandler" method="GET">
								<input type="hidden" name="productID" value="RAM_008">
								<button class="banner-button" type="submit">
									<img src="images/preview_img/slider/img-4.jpg" alt="Slide 4">
								</button>
							</form>
						</div>
					</div>
					<div class="swiper-pagination"></div>
					<div class="swiper-button-prev"></div>
					<div class="swiper-button-next"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="scripts/swiper.js"></script>
<c:import url="/includes/footer.jsp" />
</html>
