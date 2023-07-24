<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.User, model.CreditCard, java.text.*"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link rel="icon" href="../images/windowlogo-light.png">
<link href="../styles/forms.css" rel="stylesheet" type="text/css">
<link href="../styles/search.css" rel="stylesheet" type="text/css">
<link href="../styles/cart.css" rel="stylesheet" type="text/css">
<link href="../styles/checkout.css" rel="stylesheet" type="text/css">
<link href="../styles/payment.css" rel="stylesheet" type="text/css">
<script src="../scripts/validateForm.js" type="text/javascript"></script>
<title>Payment</title>
</head>
<body>

	<%@ include file="../header.jsp"%>
	<div id="main-container">

		<%
		DecimalFormat df = new DecimalFormat("#.00");
		User user = (User) session.getAttribute("activeUser");
		Collection<?> cards = (Collection<?>) request.getAttribute("cards");
		if (user != null) {
			if (cards == null) {
				request.getServletContext().getRequestDispatcher("/RetrieveCreditCards").forward(request, response);
			} else if (cards.size() == 0) {
		%>
		<p class="error">There are no cards registered to your account,
			please insert your details to complete the payment.</p>

		<div class="form-container">
			<h3>Credit card details</h3>
			<form action="../CreditCardAdd" method="post"
				onsubmit="return validateCreditCardForm(this)">
				<input type="hidden" value="<%=user.getId()%>" name="id">
				<p class="label">Card number:</p>
				<input type="text" required name="cardNumber" class="input"
					onchange="return validateCreditCardNumber(this)"
					onblur="return notEmpty(this)">
				<p class="error-message" id="cardNumber-error"></p>
				<div class="same-line-left">
					<p class="label">CVC:</p>
					<input required type="text" name="cvc" class="input same-line"
						onchange="return validateCVC(this)" onblur="return notEmpty(this)"
						placeholder="***">
					<p class="error-message" id="cvc-error"></p>
				</div>
				<div class="same-line-right">
					<p class="label">Expiration Date:</p>
					<input required type="text" name="expirationDate" id="town"
						class="input  same-line"
						onchange="return validateExpirationDate(this)"
						onblur="return notEmpty(this)" placeholder="mm/yy">
					<p class="error-message" id="expirationDate-error"></p>
				</div>
				<button type="submit" class="submit-button">Add credit cart</button>
			</form>
		</div>



		<%
		} else {
		%>
		<h3>Credit card:</h3>
		<form id="choose-card" action="../CompletePayment" method="post">
			<%
				Iterator<?> it = cards.iterator();
				while (it.hasNext()) {
					CreditCard c = (CreditCard) it.next();
				%>

			<div class="side-left">
				<div class="centered">
					<input type="radio" required name="creditCard"
						class="address-radio-button" value="<%=c.getNumber()%>">
					<div id="address-deets">
						<label for="address" id="name"><%=user.getFname()%> <%=user.getLname()%>,</label>
						<label for="address" id="creditCardNumber"><p class="p">Card Number: </p><%=c.getNumber().replaceAll("(.{4})", "$1-").substring(0, 19)%>,</label>
						<label for="address" id="expirationDate"><p class="p">Expiration date: </p><%=c.getExpiration()%></label>
					</div>
				</div>
			</div>

			<%
				}
				}
				}
				%>

			<%
				if (cards != null) {
					if (!cards.isEmpty()) {
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
				<button type="submit" id="go-to-checkout">Complete payment</button>
			</div>
		</form>
		<%
		}
		}
		%>
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



		<%@include file="../footer.jsp"%>
</body>
</html>