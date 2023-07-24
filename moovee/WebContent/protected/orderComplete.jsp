<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<link rel="icon" href="../images/windowlogo-light.png">
<link href="../styles/forms.css" rel="stylesheet" type="text/css">
<link href="../styles/search.css" rel="stylesheet" type="text/css">
<link href="../styles/cart.css" rel="stylesheet" type="text/css">
<link href="../styles/checkout.css" rel="stylesheet" type="text/css">
<link href="../styles/payment.css" rel="stylesheet" type="text/css">
<link href="../styles/orderComplete.css" rel="stylesheet" type="text/css">
<script src="../scripts/scripts.js" type="text/javascript"></script>
<meta charset="ISO-8859-1">
<title>Completed order</title>
</head>
<body onload="fixFooter()">
	<%@ include file="../header.jsp"%>

	<div id="main-container">
		<p id="success">Your order was completed successfully!</p>
		<p>You will receive your package in about three working days.</p>
		<p>Check your email to find payment's receit and a link to track
			your package.</p>
		<p>Click <a href="../index.jsp">here</a> to browse some more.</p>
	</div>

	<%@include file="../footer.jsp"%>

</body>
</html>