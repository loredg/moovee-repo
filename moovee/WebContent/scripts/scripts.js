//function to pin footer to the bottom when page does not have enough content
function fixFooter() {
	return document.getElementById("footer").classList.toggle("footer-fixed");
}

//slideshow of movies in index
let slideIndex = 1;
let interval = setInterval(showSlidesAuto, 5000);

function plusSlide(n) {
	showSlides(slideIndex += n);
	clearInterval(interval);
	interval = setInterval(showSlidesAuto, 5000);
}

function showSlides(n) {
	let i;
	let slides = document.getElementsByClassName("movie-slide");
	if (n > slides.length) { slideIndex = 1 }
	if (n < 1) { slideIndex = slides.length }
	for (i = 0; i < slides.length; i++) {
		slides[i].style.display = "none";
	}
	slides[slideIndex - 1].style.display = "block";
}

function showSlidesAuto() {
	let i;
	let slides = document.getElementsByClassName("movie-slide");
	for (i = 0; i < slides.length; i++) {
		slides[i].style.display = "none";
	}
	slideIndex++;
	if (slideIndex > slides.length) { slideIndex = 1 }
	slides[slideIndex - 1].style.display = "block";
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
