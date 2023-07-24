<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link rel="icon" href="../images/windowlogo-light.png">
<link href="../styles/forms.css" rel="stylesheet" type="text/css">
<link href="../styles/search.css" rel="stylesheet" type="text/css">
<link href="../styles/cart.css" rel="stylesheet" type="text/css">
<link href="../styles/checkout.css" rel="stylesheet" type="text/css">
<script src="../scripts/jquery.js" type="text/javascript"></script>
<script src="../scripts/validateForm.js" type="text/javascript"></script>
<title>Your moovee account</title>
</head>
<body>

	<%@ include file="../header.jsp"%>

	<%
	User user = (User) request.getSession().getAttribute("activeUser");
%>

	<div id="main-container">
		<h3>Personal data</h3>


		<div class="form-container">
			<form action="../AccountEdit" method="post">
				<input type="hidden" name="id" value="<%=user.getId()%>">
				<p class="label">Username:</p>
				<input type="text" required name="username" class="input"
					onblur="return notEmpty(this)" value="<%=user.getUsername()%>">
				<p class="error-message" id="username-error"></p>
				<p class="label">Email:</p>
				<input type="email" name="email" class="input"
					onblur="return notEmpty(this)" value="<%=user.getEmail()%>">
				<p class="error-message" id="email-error"></p>
				<p class="label">Password:</p>
				<input required type="password" name="genre" class="input same-line"
					onblur="return notEmpty(this)">
				<p class="error-message" id="password-error"></p>
				<div class="same-line-left">
					<p class="label">First name:</p>
					<input required type="text" name="fname" id="price"
						class="input  same-line" onblur="return notEmpty(this)"
						value="<%=user.getFname()%>">
					<p class="error-message" id="fname-error"></p>
				</div>
				<div class="same-line-right">
					<p class="label">Last name:</p>
					<input type="text" name="Lname" class="input"
						onblur="return notEmpty(this)" value="<%=user.getLname()%>">
					<p class="error-message" id="Lname-error"></p>
				</div>
				<button type="submit" class="submit-button">Edit account</button>
			</form>
		</div>


		<h3>Add an address</h3>
		<%
	Collection<?> addresses = (Collection<?>) request.getAttribute("addresses");
	request.setAttribute("referer", "/protected/account.jsp");
	if (user != null) {
		if (addresses == null) {
			request.getServletContext().getRequestDispatcher("/RetrieveAddresses").forward(request, response);
		} 
	%>


			<div class="form-container">
				<form action="../AddressAdd" method="post"
					onsubmit="return validateAddressForm(this)">
					<input type="hidden" value="<%=user.getId()%>" name="id">
					<p class="label">Address:</p>
					<input type="text" required name="address" class="input"
						onchange="return validateAddress(this)"
						onblur="return notEmpty(this)">
					<p class="error-message" id="address-error"></p>
					<div class="same-line-left">
						<p class="label">Zip code:</p>
						<input required type="text" name="zipCode" class="input same-line"
							onchange="return validateZipCode(this)"
							onblur="return notEmpty(this)">
						<p class="error-message" id="zipCode-error"></p>
					</div>
					<div class="same-line-right">
						<p class="label">Town:</p>
						<input required type="text" name="town" id="town"
							class="input  same-line" onchange="return validateText(this)"
							onblur="return notEmpty(this)">
						<p class="error-message" id="town-error"></p>
					</div>
					<div class="same-line-left">
						<p class="label">Province:</p>
						<input type="text" name="province" class="input"
							onchange="return validateText(this)"
							onblur="return notEmpty(this)">
						<p class="error-message" id="province-error"></p>
					</div>
					<div class="same-line-right">
						<p class="label">Region:</p>
						<input type="text" name="region" class="input"
							onchange="return validateText(this)"
							onblur="return notEmpty(this)">
						<p class="error-message" id="region-error"></p>
					</div>
					<p class="label">State:</p>
					<input type="text" name="state" class="input"
						onchange="return validateText(this)"
						onblur="return notEmpty(this)">
					<p class="error-message" id="state-error"></p>
					<button type="submit" class="submit-button">Add address</button>
				</form>
			</div>



		<%
		}
	%>


	</div>

	<%@ include file="../footer.jsp"%>

</body>
</html>