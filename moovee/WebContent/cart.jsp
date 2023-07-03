<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, model.Cart, model.Movie"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<script
	src="../scripts/jquery.js"
	type="text/javascript"></script>
<title>Moovee cart</title>
</head>
<body>

	<%@ include file="header.jsp" %>

	<h3>Your moovee shopping cart</h3>

	<%
	if (cart == null || cart.getMovies().size() == 0) {
	%>

	Your shopping cart is empty.
	<a href="./index.jsp">Click here</a> to see what's available.

	<%
	} else {
	for (Movie m : cart.getMovies()) {
	%>

	<div id="movie">
		<img src="./GetPicture?id=<%=m.getId()%>"
			onerror="this.src='./images/noimageavailable.jpg'"
			style="width: 150px; height: 200px" alt="movie">
		<div id="movie-deets">
			<label id="movie-title"><%=m.getTitle()%></label> <br> <label
				id="movie-price"><%=m.getPrice()%></label>
				<form action="./RemoveFromCart" method="post">
					<input type="hidden" value="<%=m.getId()%>" name="removeFromCart">
				<button type="submit">Remove from cart</button>
				</form>
		</div>
	</div>
	<%
	}
	%>
	<br>
	Your total is:
	<%=cart.getTotalAmount()%> <br>
	<a id="checkout" href="protected/checkout.jsp">Proceed to checkout</a>

	<%
	}
	%>

<!-- TODO: add a slideshow of suggested movies -->
	<%@ include file="../footer.jsp"%>

</body>
</html>