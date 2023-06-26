<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, model.Movie"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link href="index.css" type="text/css" rel="stylesheet">
<title>moovee</title>
</head>
<body>

	<!-- TODO: add alert to confirm registration. Get attribute hasRegistered -->

	<header>
		<div id="header-div">
			<div id="logo">
				<a href="./index.jsp" id="logo"><img id="header-logo"
					src="images/moovee-cropped-light.png" alt="moovee logo"></a>
			</div>
			<div class="header-center">
				<a id="home" class="nav-buttons" href="home">Home</a> <a id="about"
					class="nav-buttons" href="About">About</a> <a id="contacts"
					class="nav-buttons" href="Contacts">Contacts</a>
			</div>
			<div class="header-right">
				<!-- <form id="search-form">
					<input type="search" id="search-bar">
					<button type="submit" class="right-buttons" id="search">Search</button>
				</form> -->
				<a href="./cart.jsp" class="right-buttons" id="cart">Cart</a>
				<%
				Boolean isLogged = (Boolean)session.getAttribute("isLogged");
				if (isLogged == null || isLogged == false) {
				%>
				<a href="./login.jsp" class="right-buttons" id="login">Log In</a> <a
					href="./signup.jsp" class="right-buttons" id="sign-in">Sign Up</a>
				<%
				} else {
				%>
				<a href="./account.jsp" class="right-buttons" id="account">Account</a>
				<!-- <div class="user-dropdown">
					<button class="right-buttons" id="dropbtn"></button>
					<div class="dropdown-content">
						<a class="accountLinks" href="#">Account</a> <a
							class="accountLinks" href="#">Sign out</a>
					</div>
				</div> -->
				<%
				}
				%>
			</div>
		</div>
	</header>

	<h3 id="whats-new">What's new this week</h3>
	<%
	Collection<?> newMovies = (Collection<?>) request.getAttribute("newMovies");
	if (newMovies == null) {
		request.getRequestDispatcher("./NewMovies").forward(request, response);
		return;
	}

	if (newMovies != null && !newMovies.isEmpty()) {
		Iterator<?> it = newMovies.iterator();
		while (it.hasNext()) {
			Movie movie = (Movie) it.next();
	%>

	<div id="new-movies"><%=movie.getTitle()%>,&nbsp
	</div>

	<%
	}
	}
	%>

	<h3>CATALOGUE</h3>

	<%
	request.setAttribute("referer", "/index.jsp");
	Collection<?> movies = (Collection<?>) request.getAttribute("movies");
	if (movies == null) {
		request.getRequestDispatcher("./MovieDisplay").forward(request, response);
		return;
	}
	if (movies != null && movies.size() > 0) {
		Iterator<?> it = movies.iterator();
		while (it.hasNext()) {
			Movie movie = (Movie) it.next();
	%>

	<div id="movie">
		<img src="./GetPicture?id=<%=movie.getId()%>"
			onerror="this.src='./images/noimageavailable.jpg'"
			style="width: 150px; height: 200px" alt="movie">
		<div id="movie-deets">
			<label id="movie-title"><%=movie.getTitle()%></label> <br>
			<form action="./AddToCart" method="post">
				<input type="hidden" value="<%=movie.getId()%>" name="addToCart">
				<button type="submit">Add to cart</button>
			</form>
		</div>
	</div>
	<%
	}
	}
	%>


</body>
</html>