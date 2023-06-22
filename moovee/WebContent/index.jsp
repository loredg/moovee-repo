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

<%

	boolean isLogged = (boolean)session.getAttribute("isLogged");
	if(isLogged == true) {
		
%>

<script>
	alert("you successfully logged in!");
</script>

<%
	}
%>

<!-- TODO: add alert to confirm registration. Get attribute hasRegistered -->

	<header>
		<div id="header-div">
			<div id="logo">
				<a href="./index.jsp" id="logo"><img id="header-logo"
					src="images/moovee-cropped-light.png"></a>
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
				<button class="right-buttons" id="cart"></button>
				<a href="./login.jsp" class="right-buttons" id="login">Log In</a>
				<a href="./signup.jsp" class="right-buttons" id="sign-in">Sign Up</a>
				<!-- <div class="user-dropdown">
					<button class="right-buttons" id="dropbtn"></button>
					<div class="dropdown-content">
						<a class="accountLinks" href="#">Account</a> <a
							class="accountLinks" href="#">Sign out</a>
					</div>
				</div> -->
			</div>
		</div>
	</header>

	<h3 id="whats-new">What's new this week</h3>
	<%
	Collection<?> newMovies = (Collection<?>) request.getAttribute("newMovies");
	if(newMovies == null) {
		request.getRequestDispatcher("./NewMovies").forward(request, response);
		return;
	}
	
	if(newMovies!= null && !newMovies.isEmpty()) {
		Iterator<?> it = newMovies.iterator();
		while(it.hasNext()) {
			Movie movie = (Movie) it.next();
	%>
	
	<div id="new-movies"><%= movie.getTitle()%>,&nbsp</div>
	
	<%
		}
	}
	%>
	
</body>
</html>