<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.Movie, java.lang.*, java.text.*"%>
<!DOCTYPE html>
<html lang="eng">
<link href="styles/search.css" rel="stylesheet" type="text/css">
<link href="styles/index.css" type="text/css" rel="stylesheet">
<link href="styles/movie.css" rel="stylesheet" type="text/css">
<link rel="icon" href="images/windowlogo-light.png">
<head>
<meta charset="ISO-8859-1">

<%
request.setAttribute("referer", "movie.jsp");
DecimalFormat df = new DecimalFormat("#.00");
String id = request.getParameter("id");
Movie movie = (Movie) request.getAttribute("movie");
if (movie == null) {
	request.getServletContext().getRequestDispatcher("/FetchMovie?id=" + id).forward(request, response);
} else {
%>

<title><%=movie.getTitle()%></title>
</head>
<body>

	<%@ include file="header.jsp"%>

	<div id="slideshow-container">
		<div class="movie-slide fade">
			<div id="caption">
				<p id="title"><%=movie.getTitle()%></p>
				<p id="description"><%=movie.getPlot()%></p>
				<div id="secondary">
					<p id="length"><%=movie.getLength()%>
						min
					</p>
					<span></span>
					<p id="release-year"><%=movie.getReleaseYear()%></p>
					<span></span>
					<p id="genre"><%=movie.getGenre()%></p>
				</div>
			</div>
			<div id="slide-bottom">
				<form action="./AddToCart" method="post" id="add-to-cart-form">
					<input type="hidden" value="<%=movie.getId()%>" name="add-to-cart">
					<button type="submit" id="add-to-cart" class="scale">Add
						to cart</button>
				</form>
				<p id="price"><%=df.format(movie.getPrice())%>$
				</p>
				<a href="#" id="more-info" class="scale"></a>
			</div>
			<a href="movie.jsp?id=<%=movie.getId()%>"> <img
				src="./GetLandscapePicture?id=<%=movie.getId()%>"
				onerror="this.src='./images/noimageavailable.jpg'"
				alt="no image available" id="poster-slide">
			</a>
		</div>
	</div>

	<div id="details">
		<h3>Details</h3>
		<p id="director" class="primary-text">Director</p>
		<p class="secondary-text"><%=movie.getDirector()%></p>
		<p id="audio" class="primary-text">Audio language</p>
		<p class="secondary-text">English, English [Audio Description],
			Italiano, Français (Canada), Español (España), Español
			(Latinoamérica), Polski, Português, Deutsch, Français (France)</p>
		<p id="subtitles" class="primary-text">Subtitles</p>
		<p class="secondary-text">English [CC], Italiano [CC], Dansk,
			Deutsch, Deutsch [UT], Español (Latinoamérica), Español
			(Latinoamérica) [CC], Español (España) [CC], Suomi, Français [CC],
			Nederlands [CC], Nynorsk, Polski, Português (Brasil), Português
			(Brasil) [SDH], Português (Portugal), Svenska</p>

	</div>

	<%
	}
	%>

	<%@ include file="footer.jsp"%>

</body>
</html>