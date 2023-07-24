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
<script src="scripts/jquery.js" type="text/javascript"></script>
<script src="scripts/scripts.js" type="text/javascript"></script>
<script src="scripts/cart.js" type="text/javascript"></script>
<title>Moovee cart</title>
</head>
<body onload="checkHeights()">

	<%@ include file="header.jsp"%>

	<div id="main-container">
	
		<!-- TODO: add div for h3 and clearcart button -->
		<h3>Moovee shopping bag</h3>

		<%
		request.setAttribute("referer", "/cart.jsp");
		DecimalFormat df = new DecimalFormat("#.00");
		if (cart == null || cart.getMovies().size() == 0) {
		%>

		<p id="empty-cart">
			Your shopping cart is empty. <a href="./index.jsp">Click here</a> to
			see what's available.
		</p>

		<!-- TODO: ADD slide of new movies	 -->

		<%
		} else {
		HashMap<Movie, Integer> moviesInCart = cart.getMovies();
		Iterator<Movie> it = moviesInCart.keySet().iterator();
		while (it.hasNext()) {
			Movie m = it.next();
			Integer qty = moviesInCart.get(m);
		%>

		<div class="side-left">
			<a href="movie.jsp"><img
				src="./GetLandscapePicture?id=<%=m.getId()%>"
				onerror="this.src='./images/noimageavailable.jpg'" alt="movie"
				id="movie-poster"></a>
			<div class="item-description">
				<div class="title-container">
					<p class="movie-title"><%=m.getTitle()%></p>
				</div>
				<p id="movie-director" class="description">
					Directed by:
					<%=m.getDirector()%></p>
				<p id="movie-genre" class="description"><%=m.getGenre()%></p>
				<p id="movie-year" class="description"><%=m.getReleaseYear()%></p>
				<form id="remove-form" action="./RemoveFromCart" method="post">
					<input type="hidden" name="removeFromCart" value="<%=m.getId()%>">
					<button type="submit" id="remove">
						<img src="images/bin.svg" alt="" id="btn-image"> Remove
					</button>
				</form>
			</div>
			<div id="qty-container" class="dropdown">
				<form id="qty-form" action="./ChangeQty" method="post">
				<input type="hidden" name="movieID" value="<%=m.getId()%>">
					<select id="select-qty" class="dropdown-content" name="movie-qty" onchange="this.form.submit()">
						<option selected disabled hidden value="<%=qty%>"><%=qty%></option>
						<option class="options" value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</form>
				<p id="movie-price"><%=df.format(m.getPrice())%>$
				</p>
			</div>
		</div>

		<%
		}
		}
		%>


		<%
		if (cart != null) {
			if (!cart.getMovies().isEmpty()) {
		%>
		<div class="side-right">
			<div id="left">
				<p class="primary-text">Subtotal:</p>
				<p class="secondary-text">Taxes:</p>
				<p class="secondary-text">Shipping:</p>

			</div>
			<div id="right">
				<p class="primary-text"><%=df.format(cart.getTotalAmount())%>$
				</p>
				<p class="secondary-text">0.00$</p>
				<p class="secondary-text">0.00$</p>
			</div>
			<div id="total-container">
				<p class="primary-text" id="total">Total:</p>
				<p class="primary-text" id="total-number"><%=df.format(cart.getTotalAmount())%>$
				</p>
			</div>
			<form id="checkout-form" action="./Checkout" method="post">
				<button type="submit" id="go-to-checkout">Go to checkout</button>
			</form>
		</div>
		<%
		}
		}
		%>

	</div>

	<!-- TODO: add a slideshow of suggested movies -->
	<%@ include file="../footer.jsp"%>

</body>
</html>