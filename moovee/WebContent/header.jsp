<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="\"></script>
<link href="styles/header.css" rel="stylesheet" type="text/css">
<title>${param.pageTitle}</title>
</head>
<body>


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
				Boolean isLogged = (Boolean) session.getAttribute("isLogged");
				if (isLogged == null || isLogged == false) {
				%>
				<a href="./login.jsp" class="right-buttons" id="login">Log in</a>
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

</body>
</html>