<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<script src="scripts/validateForm.js" type="text/javascript"></script>
<link href="styles/login-signup.css" rel="stylesheet" type="text/css">
<title>Sign up</title>
</head>
<body>

<%@ include file="header.jsp" %>

<div class="main">

		<div class="content">

			<h2>Sign Up</h2>

			<div class="signup-form">
				<form action="./signup" method="post" onsubmit="return validateSignupForm(this)">
				<p> Name: </p>
				<input type="text" required name="First name" class="input" placeholder="Your name" onchange="return validateName(this)" onblur="return notEmpty(this)">
				<p class="error-message" id="First name-error"></p>
				<p> Last name:</p> 
				<input type="text" required name="Last name" class="input" placeholder="Your last name" onchange="return validateName(this)" onblur="return notEmpty(this)">
				<p class="error-message" id="Last name-error"></p>
				<p> Username: </p>
				<input type="text" required name="username" class="input" placeholder="Pick a cool one" onchange="return validateUsername(this)" onblur="return notEmpty(this)">
				<p class="error-message" id="username-error"></p>
				<p> Email: </p>
						<input type="email" required placeholder="your@email.com" class="input" name="email" onchange="return validateEmail(this)" onblur="return notEmpty(this)">
				<p class="error-message" id="email-error"></p>
				<p> Password: </p>
						<input type="password" required placeholder="Password" class="input" name="password" onchange="return validatePassword(this)" onblur="return notEmpty(this)">
					<p class="error-message" id="password-error"></p>
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