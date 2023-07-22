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