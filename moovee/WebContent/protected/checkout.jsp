<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.User, model.Address, java.text.*"%>
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
<title>Checkout</title>
</head>
<body>

	<%@ include file="../header.jsp"%>

	<%
	DecimalFormat df = new DecimalFormat("#.00");
	if (isLogged == null || isLogged == false) {
	%>
	You need to be logged in to proceed to checkout. Click
	<a href="../login.jsp">here</a> to log in.
	<%
	} else {
	User user = (User) session.getAttribute("activeUser");
	if (user != null) {
		Collection<?> addresses = (Collection<?>)request.getSession().getAttribute("addresses");
		if (addresses == null || addresses.size() == 0) {
	%>

	<div class="main">

		<div class="content">

			<h2>Address</h2>

			<p id="no-address" class="error">Seems like you don't have any
				addresses registered.</p>
			<div class="form">
				<form action="../AddressAdd" method="post"
					onsubmit="return validateAddressForm(this)">
					<input type="hidden" value="<%=user.getId()%>" name="id">
					<p>Address:</p>
					<input type="text" required name="address" class="input"
						onchange="return validateAddress(this)"
						onblur="return notEmpty(this)">
					<p class="error-message" id="address-error"></p>
					<p>Zip code:</p>
					<input required type="text" name="zipCode" class="input"
						onchange="return validateZipCode(this)"
						onblur="return notEmpty(this)">
					<p class="error-message" id="zipCode-error"></p>
					<p>Town:</p>
					<input required type="text" name="town" class="input"
						onchange="return validateText(this)"
						onblur="return notEmpty(this)">
					<p class="error-message" id="town-error"></p>
					<p>Province:</p>
					<input type="text" name="province" class="input"
						onchange="return validateText(this)">
					<p class="error-message" id="province-error"></p>
					<p>Region:</p>
					<input type="text" name="region" class="input"
						onchange="return validateText(this)"
						onblur="return notEmpty(this)">
					<p class="error-message" id="region-error"></p>
					<p>State:</p>
					<input type="text" name="state" class="input"
						onchange="return validateText(this)"
						onblur="return notEmpty(this)">
					<p class="error-message" id="state-error"></p>
					<button type="submit" class="submit-button">Add address</button>
				</form>
			</div>
		</div>
	</div>

	<%
	} else {
	%>
	<div id="main-container">
		<h3>Please select the address you would like your order sent to:</h3>
		<%
		for (Address a : user.getAddresses()) {
		%>
		<div id="side-left">
			<input type="radio" name="address" class="address-radio-button"
				value="<%=a.getAddressId()%>">
			<div id="address-deets">
				<label for="address" id="name"><%=user.getFname()%>
					<%=user.getLname()%>,</label>
				<label for="address" id="address"><%=a.getAddress()%>,</label>
				<label for="address" id="zip-town"><%=a.getZipCode()%>,
					<%=a.getTown()%>,</label>
				<label for="address" id="state"><%=a.getState()%></label>
			</div>
		</div>
		<%
		}
		}
		}
		}
		%>

		<div id="side-right">
			<div id="left">
				<p class="primary-text">Subtotal:</p>
				<p class="secondary-text">Taxes:</p>
				<p class="secondary-text">Shipping:</p>
			</div>
			<div id="right">
				<p class="primary-text"><%=df.format(cart.getTotalAmount())%>$
				</p>
				<p class="secondary-text">0.00$</p>
				<p class="secondary-text">0.00$</p>
			</div>
			<div id="total-container">
				<p class="primary-text" id="total">Total:</p>
				<p class="primary-text" id="total-number"><%=df.format(cart.getTotalAmount())%>$
				</p>
			</div>
			<form id="checkout-form" action="./Payment" method="post">
				<button onclick="submitForms()" id="go-to-checkout">Go to
					payment</button>
			</form>
		</div>
	</div>

	<%@include file="../footer.jsp"%>
</body>
</html>