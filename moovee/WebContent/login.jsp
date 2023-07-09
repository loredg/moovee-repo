<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link rel="icon" href="images/windowlogo-light.png">
<link href="styles/login-signup.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/scripts.js"></script>
<title>Log In</title>
</head>
<body >

	<%@ include file="header.jsp"%>


	<div class="main">

		<div class="content">

			<h2>Log In</h2>

			<%
			Collection<?> errors = (Collection<?>) request.getAttribute("errors");
			if (errors != null && errors.size() > 0) {
				Iterator<?> it = errors.iterator();
				while (it.hasNext()) {
					String error = (String) it.next();
			%>

			<p class="error"><%=error%></p>

			<%
			}
			}
			%>

			<div class="login-form">
				<form action="./login" method="post">
				<p> Email: </p>
						<input type="email" required placeholder="your@email.com" class="input" name="email">
				<p> Password: </p>
						<input type="password" required placeholder="Password" class="input" name="password">
					<div class="link">
						Don't have an account? Sign up <a href="signup.jsp">here</a>!
					</div>
					<button type="submit" value="Login" class="submit-button">Log
						In</button>
				</form>
			</div>
		</div>

	</div>

	<%@include file="footer.jsp"%>

</body>
</html>