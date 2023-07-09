<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link href="styles/login-signup.css" rel="stylesheet" type="text/css">
<title>Sign up</title>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="main">

		<div class="content">

			<h2>Sign Up</h2>

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

			<div class="signup-form">
				<form action="./signup" method="post">
				<p> Name: </p>
				<input type="text" required name="fname" class="input" placeholder="Your name">
				<p> Last name:</p> 
				<input type="text" required name="lname" class="input" placeholder="Your last name">
				<p> Username: </p>
				<input type="text" required name="username" class="input" placeholder="Pick a cool one">
				<p> Email: </p>
						<input type="email" required placeholder="your@email.com" class="input" name="email">
				<p> Password: </p>
						<input type="password" required placeholder="Password" class="input" name="password">
					<div class="link">
						Already have an account? Log in <a href="login.jsp">here</a>!
					</div>
					<button type="submit" value="Login" class="submit-button">Sign
						Up</button>
				</form>
			</div>
		</div>

	</div>
	
	<%@include file="footer.jsp" %>


</body>
</html>