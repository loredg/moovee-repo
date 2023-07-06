<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, model.Cart"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<script src="<%=request.getContextPath()%>/scripts/jquery.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/styles/header.css" rel="stylesheet" type="text/css">
<title>${param.title}</title>
</head>
<body>

<%System.out.println(request.getRequestURL()); %>
	<header>
		<div id="logo">
			<a href="<%=request.getContextPath()%>/index.jsp" id="logo"><img id="header-logo"
				src="<%=request.getContextPath()%>/images/moovee-cropped-light.png" alt="moovee logo"></a>
		</div>
		<div class="header-center">
			<a id="home" class="nav-buttons" href="<%=request.getContextPath()%>/index.jsp">Home</a> <a id="about"
				class="nav-buttons" href="About">About</a> <a id="contacts"
				class="nav-buttons" href="Contacts">Contacts</a>
		</div>
		<div class="header-right">
			<form id="search-form" action="./Search" method="post">
					<input type="search" id="search-bar" name="title" placeholder="Insert movie title...">
					<button type="submit" class="right-buttons" id="search">Search</button>
				</form>
				
				<%
				Cart cart = (Cart)session.getAttribute("cart");
				int cartItems;
				if(cart == null) {
					cartItems = 0;
				}else {
					cartItems = cart.getNumberOfMovies();
				}
				%>
			<a href="<%=request.getContextPath() %>/cart.jsp" class="right-buttons" id="cart"
				title="Shopping cart">Cart</a>
				<span id="cart-items"><%=cartItems%></span>
			<%
			Boolean isLogged = (Boolean) session.getAttribute("isLogged");
			if (isLogged == null || isLogged == false) {
			%>
			<a href="<%=request.getContextPath()%>/login.jsp" class="right-buttons" id="login" title="Log in">Log
				in</a>
			<%
			} else {
			%>
			<a href="<%=request.getContextPath()%>/account.jsp" class="right-buttons" id="account"
				title="Account">Account</a>
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
	</header>

</body>
</html>