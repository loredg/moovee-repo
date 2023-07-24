function checkHeights() {
	let title = document.getElementsByClassName("title-container");
	for(const element of title) {
		if(element.getBoundingClientRect().width > 150 && $(window).width() <= 764) {
			element.children[0].style.fontSize = "11px";
		}
		else if ($(window).width() > 737) {
			element.children[0].style.fontSize = "24px";
		}
	}
}

$(window).resize(checkHeights);

function submitForms() {
	document.getElementById("qty-form").submit();
	document.getElementById("checkout-form").submit();
}