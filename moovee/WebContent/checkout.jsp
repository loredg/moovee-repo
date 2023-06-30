<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, model.User, model.Address"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
</head>
<body>

	<%@ include file="header.jsp" %>

	<%
	if (isLogged == false || isLogged == null) {
	%>

	You need to be logged in to proceed to checkout. Click
	<a href="login.jsp">here</a> to log in.
	<%
	} else {
	User user = (User) session.getAttribute("activeUser");
	if (user.getAddresses() == null) {
	%>

	<p>Seems like you don't have any addresses registered.</p>
	<form>
	<br> Zip code:
	<input type="text" name="zipCode">
	<br> Town:
	<input type="text" name="town">
	<br> Province:
	<input type="text" name="province
	">
	<br> Region:
	<input type="text" name="region">
	<br> State:
	<input type="text" name="state">
	<br>
	<button type="submit"></button>
	<button type="reset"></button>
	<br>
	</form>

	<%
	} else {
		List<Address> addresses = user.getAddresses();
		for(Address a : user.getAddresses()) {
		%>
	<p>Please select the address you would like your order sent to:</p>
	<form action="./payment" method="post">
		<input type="radio" class="address-radio-button"
			value="<%=a.getAddressId()%>"><%=a.getAddress()%><br>
		<button type="submit">Proceed to payment</button>
	</form>
	<%
	}
	}
	}
	%>

</body>
</html>