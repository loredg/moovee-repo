<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, model.User"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
</head>
<body>

	<%
	Boolean isLogged = (Boolean) session.getAttribute("isLogged");
	if (isLogged == false || isLogged == null) {
	%>

	You need to be logged in to proceed to checkout. Click
	<a href="login.jsp">here</a> to log in.
	<%
	} else {
	User user = (User) session.getAttribute("activeUser");
	if (user.getAddresses() == null) {
	%>

	Seems like you don't have any addresses registered. 
	<form action="./AddressAdd" method="post">
		<input type="hidden" name="id" value="<%=user.getId()%>">
		Address: <input type="text" name="address"> <br>
		Zip code: <input type="text" name="zipCode"> <br>
		Town: <input type="text" name="town"> <br>
		Province: <input type="text" name="province"> <br>
		Region: <input type="text" name="region"> <br>
		State: <input type="text" name="state"> <br>
		<button type="submit"></button> <button type="reset"></button> <br>
	</form>

	<%
	} else {

	}
	}
	%>

</body>
</html>