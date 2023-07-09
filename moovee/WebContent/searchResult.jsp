<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, model.Movie, java.text.*"%>
<!DOCTYPE html>
<html lang="eng">
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
		<p id="filter-by">Filter by:</p>
		<div class="dropdown">
			<button id="genre" class="filter-buttons">
				Genre <img src="images/chevron.svg" class="chevron" alt="">
			</button>
			<div id="genre-menu" class="dropdown-content">
				<form action="./Search" method="post" class="search-form-dropdown">
					<input type="search" class="search-bar-dropdown">
					<button type="submit" class="search-button-dropdown" title="Search"></button>
				</form>
				<form action="./FilterSearch" method="post">
				<ul>
					<li class="dropdown-options"><button type="submit" class="submit-button">Action</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">Adventure</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">Comedy</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">Crime</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">Drama</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">Fantasy</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">Horror</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">Romance</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">Thriller</button></li>
				</ul>
				</form>
			</div>
		</div>
		<div class="dropdown">
			<button id="release_year" class="filter-buttons">
				Release Year <img src="images/chevron.svg" class="chevron" alt="">
			</button>
			<div id="release-year-menu" class="dropdown-content">
				<form action="./Search" method="post" class="search-form-dropdown">
					<input type="search" class="search-bar-dropdown">
					<button type="submit" class="search-button-dropdown" title="Search"></button>
				</form>
				<form action="./FilterSearch" method="post">
				<ul>
					<li class="dropdown-options"><button type="submit" class="submit-button">Before 1970s</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">1980s</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">1990s</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">2000s</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">2010s</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">2020s</button></li>
				</ul>
				</form>
			</div>
		</div>
		<div class="dropdown" id="dropdown-price">
			<button id="price-filter" class="filter-buttons">
				Price <img src="images/chevron.svg" class="chevron" alt="">
			</button>
			<div id="price-menu" class="dropdown-content">
				<form action="./FilterSearch" method="post" class="search-form-dropdown">
					<input type="search" class="search-bar-dropdown">
					<button type="submit" class="search-button-dropdown" title="Search"></button>
				</form>
				<form action="./FilterSearch" method="post">
				<ul>
					<li class="dropdown-options"><button type="submit" class="submit-button">Below 5$</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">Below 10$</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">Below 20$</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button">Below 30$</button></li>
				</ul>
				</form>
			</div>
		</div>
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
			<a href="movie.jsp"><img
				src="./GetLandscapePicture?id=<%=movie.getId()%>"
				onerror="this.src='./images/noimageavailable.jpg'" alt="movie"
				id="movie-poster"></a>
			<p id="movie-title"><%=movie.getTitle()%></p>
			<div id="container-bottom">
				<form action="./AddToCart" method="post" id="form">
					<button type="submit" id="add-to-cart" class="scale">Add
						to cart</button>
					<p id="movie-price"><%=df.format(movie.getPrice())%>$
					</p>
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