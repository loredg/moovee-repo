function checkHeights() {
	let title = document.getElementsByClassName("title-container");
	for(let i = 0; i < title.length; i++) {
		if(title[i].getBoundingClientRect().width > 150 && $(window).width() <= 764) {
			title[i].children[0].style.fontSize = "11px";
		}
		else if ($(window).width() > 737) {
			title[i].children[0].style.fontSize = "24px";
		}
	}
}

$(window).resize(checkHeights);

function submitForms() {
	document.getElementById("qty-form").submit();
	document.getElementById("checkout-form").submit();
}