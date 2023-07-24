<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.Movie, java.text.*"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="styles/index.css" type="text/css" rel="stylesheet">
<link rel="icon" href="images/windowlogo-light.png">
<script src="scripts/slides.js" type="text/javascript"></script>
<script src="scripts/scripts.js" type="text/javascript"></script>
<title>moovee</title>
</head>
<body onload="showSlides(0); showSlidesAuto(); setActive('home');">

	<!-- TODO: add alert to confirm registration. Get attribute hasRegistered -->

	<%@ include file="header.jsp"%>
	<%
	Collection<?> newMovies = (Collection<?>) request.getAttribute("newMovies");
	if (newMovies == null) {
		request.getRequestDispatcher("./NewMovies").forward(request, response);
		return;
	}

	if (newMovies != null && !newMovies.isEmpty()) {
		Iterator<?> it = newMovies.iterator();
		while (it.hasNext()) {
			Movie movie = (Movie) it.next();
	%>

	<%
	}
	}
	%>

	<div id="slideshow-container">

		<%
		DecimalFormat df = new DecimalFormat("#.00");
		request.setAttribute("referer", "/index.jsp");
		Collection<?> movies = (Collection<?>) request.getAttribute("movies");
		if (movies == null) {
			request.getRequestDispatcher("./MovieDisplay").forward(request, response);
			return;
		}
		if (movies != null && movies.size() > 0) {
			Iterator<?> it = movies.iterator();
			int index = 1;
			while (it.hasNext()) {
				Movie movie = (Movie) it.next();
				if (movie.getQty() >= 0) {
		%>
		<div class="movie-slide fade">
			<div id="caption">
				<p id="title"><%=movie.getTitle()%></p>
			</div>
			<div id="slide-bottom">
				<form action="./AddToCart" method="post" id="add-to-cart-form">
					<input type="hidden" value="<%=movie.getId()%>" name="add-to-cart">
					<button type="submit" id="add-to-cart" class="scale">Add
						to cart</button>
				</form>
				<p id="price"><%=df.format(movie.getPrice())%>$
				</p>
				<form action="./FetchMovie" method="post">
					<a href="movie.jsp" id="more-info" class="scale" onclick=""></a>
				</form>
			</div>
			<a href="movie.jsp?id=<%=movie.getId()%>"> <img
				src="./GetLandscapePicture?id=<%=movie.getId()%>"
				onerror="this.src='./images/noimageavailable.jpg'"
				alt="no image available" id="poster-slide">
			</a>
		</div>

		<%-- <div id="movie">
		<img src="./GetPicture?id=<%=movie.getId()%>"
			onerror="this.src='./images/noimageavailable.jpg'"
			style="width: 150px; height: 200px" alt="movie">
		<div id="movie-deets">
			<label id="movie-title"><%=movie.getTitle()%></label> <br>
			<form action="./AddToCart" method="post">
				<input type="hidden" value="<%=movie.getId()%>" name="addToCart">
				<%
				if(movie.getQty() == 0) {
				%>
				<p>This movie is not available at the moment.</p>
				<%}else { %>
				<button type="submit">Add to cart</button>
				<%}%>
			</form>
		</div>
	</div> --%>
		<%
		}
		index++;
		}
		}
		%>
		<a id="prev" onclick="plusSlide(-1)">&#10094;</a> <a id="next"
			onclick="plusSlide(1)">&#10095;</a>

	</div>
<%-- 
	<%
	Collection<?> action = (Collection<?>) request.getAttribute("moviesSearched");
	if (action == null) {
		request.getServletContext().getRequestDispatcher("/FilterSearch?genere=action").forward(request, response);
	}
	if (!action.isEmpty()) {
	%>
	<div class="horizontal-scroll-wrapper squares">
		<%
		Iterator<?> it = action.iterator();
		while (it.hasNext()) {
			Movie m = (Movie) it.next();
			if (m.getQty() > 0) {
		%>
		<div>
			<img src="./GetLandscapePoster?id=<%=m.getId()%>">
		</div>
		<%
		}
		}
		}
		%>

	</div> --%>


	<%@ include file="footer.jsp"%>

</body>
</html>