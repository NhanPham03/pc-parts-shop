<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<body>
	<div class="container">
		<header>
			<div class="header-logo">
				<a href="home" itemprop="url">
					<img src="images/Logo.png" alt="Logo">
				</a>
			</div>
			<div class="separator"></div>
				<div class="header-items">
					<div class="header-home">
						<a href="home" aria-label="Home" title="Home">Home</a>
					</div>
					<div class="header-search">
						<form action="catalogHandler" method="GET">
							<input type="hidden" name="action" value="search">
							<input class="input-search" type="text" name="q" maxlength="40" style="width: -webkit-fill-available;" placeholder="Looking for something?" value="${query}">
							<input class="button-search" type="submit" value="Search">
						</form>
					</div>
					<div class="header-cart">
						<a href="cartHandler" aria-label="Cart" title="Cart">Cart</a>
					</div>
					<div class="header-account">
						<a href="accountHandler" aria-label="Account" title="Account">Account</a>
					</div>
				</div>
			<div class="separator"></div>
		</header>
	</div>
		