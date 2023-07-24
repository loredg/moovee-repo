function notEmpty(input) {
	if (input.value == "") {
		input.classList.add("wrong-input");
		document.getElementById(input.name + "-error").innerHTML = "Field cannot be empty";
		return false;
	}

	return true;
}

function validateName(input) {
	let pattern = /^[A-Za-z ]+$/;
	if (input.value.match(pattern)) {
		input.classList.remove("wrong-input");
		document.getElementById(input.name + "-error").innerHTML = "";
		return true;
	} else {
		input.classList.add("wrong-input");
		document.getElementById(input.name + "-error").innerHTML = input.name + " can contain only letters";
		return false;
	}
}

function validateUsername(input) {
	let pattern = /^[A-Za-z0-9._]{8,20}$/;
	if (input.value.match(pattern)) {
		input.classList.remove("wrong-input");
		document.getElementById("username-error").innerHTML = "";
		return true;
	} else {
		input.classList.add("wrong-input");
		document.getElementById("username-error").innerHTML = "Username can contain letters, numbers, underscores and dots and must be between 8 and 20 characters long";
		return false;
	}
}

function validateEmail(input) {
	let pattern = /^\S+@\S+\.\S+$/;
	if (input.value.match(pattern)) {
		input.classList.remove("wrong-input");
		document.getElementById("email-error").innerHTML = "";
		return true;
	} else {
		input.classList.add("wrong-input");
		document.getElementById("email-error").innerHTML = "Invalid email address";
		return false;
	}
}

function validatePassword(input) {
	if (input.value.length < 8) {
		input.classList.add("wrong-input");
		document.getElementById("password-error").innerHTML = "Password must be atleast 8 characters long";
		return false;
	}
	else {
		input.classList.remove("wrong-input");
		document.getElementById("password-error").innerHTML = "";
		return true;
	}
}

function validateSignupForm(form) {
	return (validateName(form.elements["First name"]) &&
		validateName(form.elements["Last name"]) &&
		validateUsername(form.username) &&
		validateEmail(form.email) &&
		validatePassword(form.password));
}

function validateLoginForm(form) {
	return (validateEmail(form.email) &&
		validatePassword(form.password));
}

function validateAddress(input) {
	let pattern = /^\w+[\w\s]+\w+$/;
	console.log("ciao");
	if (input.value.match(pattern)) {
		input.classList.remove("wrong-input");
		document.getElementById("address-error").innerHTML = "";
		return true;
	} else {
		input.classList.add("wrong-input");
		document.getElementById("address-error").innerHTML = "Address can only contain letters and numbers";
		return false;
	}
}

function validateZipCode(input) {
	let pattern = /\d{5}/;
	if (input.value.match(pattern)) {
		input.classList.remove("wrong-input");
		document.getElementById("zipCode-error").innerHTML = "";
		return true;
	} else {
		input.classList.add("wrong-input");
		document.getElementById("zipCode-error").innerHTML = "Zip code must be 5 digits";
		return false;
	}
}

function validateText(input) {
	let pattern = /^[A-Za-z\s]*$/;
	if (input.value.match(pattern)) {
		input.classList.remove("wrong-input");
		document.getElementById(input.name + "-error").innerHTML = "";
		return true;
	} else {
		input.classList.add("wrong-input");
		document.getElementById(input.name + "-error").innerHTML = "Field can only contain letters and spaces.";
		return false;
	}
}

function validateAddressForm(form) {
	return (validateAddress(form.address) && validateZipCode(form.zipCode) && validateText(form.town) && validateText(form.province) && validateText(form.region) && validateText(form.state));
}

function validateCreditCardNumber(input) {
	let pattern = /\b\d{4}[ -]?\d{4}[ -]?\d{4}[ -]?\d{4}\b/;
	if (input.value.match(pattern)) {
		input.classList.remove("wrong-input");
		document.getElementById(input.name + "-error").innerHTML = "";
		return true;
	} else {
		input.classList.add("wrong-input");
		document.getElementById(input.name + "-error").innerHTML = "Credit card number must be 16 digits long. Only dashes and spaces are allowed.";
		return false;
	}
}

function validateCVC(input) {
	let pattern = /[0-9]{3}/;
	if (input.value.match(pattern)) {
		input.classList.remove("wrong-input");
		document.getElementById(input.name + "-error").innerHTML = "";
		return true;
	} else {
		input.classList.add("wrong-input");
		document.getElementById(input.name + "-error").innerHTML = "CVC must be three digits.";
		return false;
	}
}

function validateExpirationDate(input) {
	let pattern = /^[0-9]{1,2}\/[0-9]{2}$/;
	if (input.value.match(pattern)) {
		input.classList.remove("wrong-input");
		document.getElementById(input.name + "-error").innerHTML = "";
		return true;
	} else {
		input.classList.add("wrong-input");
		document.getElementById(input.name + "-error").innerHTML = "Expiration date must be in the format mm/yy.";
		return false;
	}
}

function validateCreditCardForm(form) {
	return (validateCreditCardNumber(form.cardNumber) &&
		validateCVC(form.cvc) && validateExpirationDate(form.expirationDate));
}
