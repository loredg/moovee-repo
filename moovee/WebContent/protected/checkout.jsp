<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.User, model.Address, java.text.*, java.lang.*, org.apache.*"%>
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
<script src="../scripts/checkout.js" type="text/javascript"></script>
<title>Checkout</title>
</head>
<body>

	<%@ include file="../header.jsp"%>

	<%
	String referer = "/protected/checkout.jsp";
	request.setAttribute("referer", referer);
	DecimalFormat df = new DecimalFormat("#.00");
	User user = (User) session.getAttribute("activeUser");
	Collection<?> addresses = (Collection<?>) request.getAttribute("addresses");
	if (user != null) {
		if (addresses == null) {
			request.getServletContext().getRequestDispatcher("/RetrieveAddresses").forward(request, response);
		} else if (addresses.size() == 0) {
	%>

	<div id="main-container">
		<p id="no-address" class="error">Seems like you don't have any
			addresses registered.</p>
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
					onchange="return validateText(this)" onblur="return notEmpty(this)">
				<p class="error-message" id="state-error"></p>
				<button type="submit" class="submit-button">Add address</button>
			</form>
		</div>

	</div>

	<%
	} else {
	%>
	<div id="main-container">
		<h3>Delivery address:</h3>
		<form id="choose-address" action="../Payment" method="post">
			<%
			Iterator<?> it = addresses.iterator();
			while (it.hasNext()) {
				Address a = (Address) it.next();
			%>

			<div class="side-left">
				<div class="centered">
					<input type="radio" required name="address"
						class="address-radio-button" value="<%=a.getAddressId()%>">
					<div id="address-deets">
						<label for="address" id="name"><%=user.getFname()%> <%=user.getLname()%>,</label>
						<label for="address" id="address"><%=a.getAddress()%>,</label> <label
							for="address" id="zip-town"><%=a.getZipCode()%>, <%=a.getTown()%>,</label>
						<label for="address" id="state"><%=a.getState()%></label>
					</div>
				</div>
			</div>
			<%
			}
			}
			}
			%>
			
			<%
		if (cart != null) {
			if (!cart.getMovies().isEmpty()) {
		%>

			<div class="side-right">
				<div id="left">
					<p class="primary-text">Subtotal:</p>
					<p class="secondary-text">Taxes:</p>
					<p class="secondary-text">Shipping:</p>

				</div>
				<div id="right">
					<p class="primary-text" id="subtotal-number"><%=df.format(cart.getTotalAmount())%>$
					</p>
					<p class="secondary-text" id="shipping">0.00$</p>
					<p class="secondary-text" id="taxes">0.00$</p>
				</div>
				<div id="total-container">
					<p class="primary-text" id="total">Total:</p>
					<p class="primary-text" id="total-number"><%=df.format(cart.getTotalAmount())%>$
					</p>
				</div>
				<input type="hidden" name="id" value="<%=user.getId()%>">
				<button type="submit" id="go-to-checkout">Go to payment</button>
			</div>
		</form>

		<div class="side-left" id="payment-options-container">
			<h3 id="payment-heading">Payment options</h3>
			<p id="payment-options">
				<img src="../images/credit-card.svg" alt="" id="credit-card-logo">Credit/Debit
				card
			</p>
			<div id="logos">
				<img src="../images/visa-logo.png" alt="Visa" class="card-logo">
				<img src="../images/Mastercard-logo.png" alt="MasterCard"
					class="card-logo"> <img src="../images/maestro-logo.png"
					alt="maestro" class="card-logo">
			</div>
		</div>
		<%
		}
		}
		%>

	</div>

	<%@include file="../footer.jsp"%>
</body>
</html>