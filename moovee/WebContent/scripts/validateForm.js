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
