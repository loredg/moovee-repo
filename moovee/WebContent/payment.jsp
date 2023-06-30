<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*, model.User, model.CreditCard"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payment</title>
</head>
<body>

<%@ include file="header.jsp" %>

<%
User user = (User)session.getAttribute("activeUser"); 
List<CreditCard> cards = user.getCards();
if(cards == null || cards.isEmpty()) {
	%>
	<p>There are no cards registered to your account, please insert your details to complete the payment.</p>
	<form action="./Payment" method="post">
		Card number: <input type="text" name="number"> <br>
		Expiration date: <input type="text" name="expiration"> <br>
		CVC: <input type="text" name="cvc"> <br>
		<button type="submit">Add card</button>
	</form>
	<%
}
%>

</body>
</html>