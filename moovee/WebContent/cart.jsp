<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.Cart, model.Movie, java.text.*"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link href="styles/index.css" rel="stylesheet" type="text/css">
<link href="styles/search.css" rel="stylesheet" type="text/css">
<link href="styles/cart.css" rel="stylesheet" type="text/css">
<link rel="icon" href="images/windowlogo-light.png">
<script src="../scripts/jquery.js" type="text/javascript"></script>
<title>Moovee cart</title>
</head>
<body>

	<%@ include file="header.jsp"%>

	<div id="main-container">

		<div id="side-left">
			<h3>Moovee shopping bag</h3>

			<%
			if (cart == null || cart.getMovies().size() == 0) {
			%>

			Your shopping cart is empty. <a href="./index.jsp">Click here</a> to
			see what's available.

			<%
			} else {
			for (Movie m : cart.getMovies()) {
			%>

			<%-- <div id="movie-container" class="scale">
			<a href="movie.jsp"><img
				src="./GetLandscapePicture?id=<%=m.getId()%>"
				onerror="this.src='./images/noimageavailable.jpg'" alt="movie"
				id="movie-poster"></a>
			<p id="movie-title"><%=m.getTitle()%></p>
			<div id="container-bottom">
				<div id="qty">
					<button id="qty" class="dropdown">
						Q.ty:
						<%=Collections.frequency(cart.getMovies(), m)%>
						<img src="images/chevron.svg" class="chevron" alt="">
						<div class="dropdown-content">
				
						</div>
					</button>
				</div>
			</div>
		</div> --%>
<!-- TODO: group below in div -->
			<a href="movie.jsp"><img
				src="./GetLandscapePicture?id=<%=m.getId()%>"
				onerror="this.src='./images/noimageavailable.jpg'" alt="movie"
				id="movie-poster"></a>
			<p id="movie-title"><%=m.getTitle()%></p>
			<form id="remove-form" action="./RemoveFromCart" method="post">
			<input type="hidden" name="remove-from-cart" value="<%=m.getId() %>">
				<button type="submit" id="remove"><img src="images/bin.svg" alt="" id="btn-image">Remove</button>
			</form>

			<%
			}
			}
			%>

		</div>

		<div id="side-right">
			<p>
				Subtotal :
				<%=cart.getTotalAmount()%></p>
		</div>

	</div>

	<!-- TODO: add a slideshow of suggested movies -->
	<%@ include file="../footer.jsp"%>

</body>
</html>