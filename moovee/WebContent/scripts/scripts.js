function fixFooter() {
	return document.getElementById("footer").classList.toggle("footer-fixed");
}

function setActive(buttonName) {
	let button = document.getElementById(buttonName);
	button.classList.add("active");
}

//display pop-up menu in header 
function popUpMenu() {
	let popupmenu = document.getElementById("pop-up-menu");
	if (popupmenu.style.width == "0") {
		popupmenu.style.width = "250px";
	}
	else {
		popupmenu.style.width = "0";
	}
}

//functions to open and close sidenav in index
function openNav() {
	document.getElementById("sidenav").style.width = "250px";

}

function closeNav() {
	document.getElementById("sidenav").style.width = "0px";
}
