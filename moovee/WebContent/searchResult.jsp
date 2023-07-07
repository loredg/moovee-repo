<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.Movie, java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="styles/index.css" rel="stylesheet" type="text/css">
<link href="styles/search.css" rel="stylesheet" type="text/css">
<link rel="icon" href="images/windowlogo-light.png">
<script src="scripts/scripts.js" type="text/javascript"></script>
<title>Search result</title>
</head>
<body>

	<%@ include file="header.jsp"%>
	
	<div id="filter">
		<p id="filter-by">Filter by: </p>
		<button id="genre" class="filter-buttons scale">Genre</button>
		<button id="release_year" class="filter-buttons scale">Release Year</button>
		<button id="price-filter" class="filter-buttons scale">Price</button>
	</div>

	<div id="main-container">
	<%
	Collection<?> moviesSearched = (Collection<?>) request.getAttribute("moviesSearched");
	if (moviesSearched == null) {
	%>
	<p>Sorry, the movie you're looking for is not in our catologue :(</p>
	<script>
		footerFix()
	</script>
	<%
	} else {
	Iterator<?> it = moviesSearched.iterator();
	DecimalFormat df = new DecimalFormat("#.00");
	while (it.hasNext()) {
		Movie movie = (Movie) it.next();
		if (movie.getQty() >= 0) {
	%>
	<div id="movie-container" class="scale">
		<a href="movie.jsp"><img src="./GetLandscapePicture?id=<%=movie.getId()%>"
			onerror="this.src='./images/noimageavailable.jpg'" alt="movie"id="movie-poster"></a>
			<p id="movie-title"><%=movie.getTitle()%></p>
			<div id="container-bottom">
				<form action="./AddToCart" method="post" id="form">
				<button type="submit" id="add-to-cart" class="scale">Add to cart</button>
				<p id="movie-price"><%=df.format(movie.getPrice()) %>$</p>
				</form>
			</div> 
	</div>
	<%
	}
	}
	}
	%>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>