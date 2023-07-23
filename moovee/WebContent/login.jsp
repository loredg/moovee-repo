<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link rel="icon" href="images/windowlogo-light.png">
<link href="styles/forms.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="scripts/validateForm.js"></script>
<title>Log In</title>
</head>
<body>

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

			<div class="form">
				<form action="./login" method="post"
					onsubmit="return validateLoginForm(this)">
					<p>Email:</p>
					<input type="email" required placeholder="your@email.com"
						class="input" name="email" onchange="return validateEmail(this)" onblur="return notEmpty(this)">
					<p class="error-message" id="email-error"></p>
					<p>Password:</p>
					<input type="password" required placeholder="Password"
						class="input" name="password" onchange="return validatePassword(this)" onblur="return notEmpty(this)">
					<p class="error-message" id="password-error"></p>
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