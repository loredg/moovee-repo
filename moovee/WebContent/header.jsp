<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, model.Cart"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<script src="<%=request.getContextPath()%>/scripts/scripts.js"
	type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/scripts/jquery.js"
	type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/styles/header.css"
	rel="stylesheet" type="text/css">
<title>${param.title}</title>
</head>
<body>

	<header>

		<div id="sidenav">
			<button id="closebtn" onclick="closeNav()">&times;</button>
			<form id="search-form-side" action="<%=request.getContextPath()%>/FilterSearch" method="post">
				<input type="hidden" name="filter" value="titolo">
				<input type="search" id="search-bar-side" name="filterValue"
					placeholder="Insert movie title...">
				<button type="submit" class="right-buttons" id="search-side"></button>
			</form>
			<a href="#">About</a> 
			<a href="#">Contacts</a>
		</div>
		<button id="three-lines" class="right-buttons" onclick="openNav()"></button>
		<div id="logo">
			<a href="<%=request.getContextPath()%>/index.jsp" id="logo"><img
				id="header-logo"
				src="<%=request.getContextPath()%>/images/moovee-cropped-light.png"
				alt="moovee logo"></a>
		</div>
		<div class="header-center">
			<a id="home" class="nav-buttons"
				href="<%=request.getContextPath()%>/index.jsp">Home</a> <a
				id="about" class="nav-buttons"
				href="<%=request.getContextPath()%>/about.jsp">About</a> <a
				id="contacts" class="nav-buttons"
				href="<%=request.getContextPath()%>/contacts.jsp">Contacts</a>
		</div>
		<div id="header-right">
			<form id="search-form" action="./FilterSearch" method="post">
			<input type="hidden" name="filter" value="titolo">
				<input type="search" id="search-bar-main" name="filterValue"
					placeholder="Type movie title...">
				<button type="submit" class="right-buttons" id="search-main"></button>
			</form>

			<%
			Cart cart = (Cart) session.getAttribute("cart");
			int cartItems;
			if (cart == null) {
				cartItems = 0;
			} else {
				cartItems = cart.getNumberOfMovies();
			}
			%>
			<a href="<%=request.getContextPath()%>/cart.jsp"
				class="right-buttons" id="cart" title="Shopping cart"></a> <span
				id="cart-items"><%=cartItems%></span>
			<%
			Boolean isLogged = (Boolean) session.getAttribute("isLogged");
			if (isLogged == null || isLogged == false) {
			%>
			<a href="<%=request.getContextPath()%>/login.jsp"
				class="right-buttons" id="login" title="Log in"></a>
			<%
			} else {
			%>
			<a href="<%=request.getContextPath()%>/account.jsp"
				class="right-buttons" id="account" title="Account"></a>
			<%
			}
			%>
		</div>
	</header>

</body>
</html>