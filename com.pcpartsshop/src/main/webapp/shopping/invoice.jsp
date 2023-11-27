<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="main.css" type="text/css">
</head>
<c:import url="/includes/header.jsp" />
	<div class="container">
		<c:out value="${invoiceResult}" escapeXml="false" />
	</div>
<c:import url="/includes/footer.jsp" />
</html>