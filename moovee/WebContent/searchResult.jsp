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
				<form action="./FilterSearch" method="post" class="search-form-dropdown">
					<input type="hidden" name="filter" value="genere">
					<input type="search" class="search-bar-dropdown" name="filterValue">
					<button type="submit" class="search-button-dropdown" title="Search"></button>
				</form>
				<form action="./FilterSearch" method="post">
				<input type="hidden" name="filter" value="genere">
				<ul>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="Action">Action</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="Adventure">Adventure</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="Comedy">Comedy</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="Crime">Crime</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="Drama">Drama</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="Fantasy">Fantasy</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="Horror">Horror</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="Romance">Romance</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="Thriller">Thriller</button></li>
				</ul>
				</form>
			</div>
		</div>
		<div class="dropdown">
			<button id="release_year" class="filter-buttons">
				Release Year <img src="images/chevron.svg" class="chevron" alt="">
			</button>
			<div id="release-year-menu" class="dropdown-content">
				<form action="./FilterSearch" method="post" class="search-form-dropdown">
					<input type="hidden" name="filter" value="anno_uscita">
					<input type="search" class="search-bar-dropdown" name="filterValue">
					<button type="submit" class="search-button-dropdown" title="Search"></button>
				</form>
				<form action="./FilterSearch" method="post">
				<input type="hidden" name="filter" value="anno_uscita">
				<ul>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="197">1970s</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="198">1980s</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="199">1990s</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="200">2000s</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="201">2010s</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="202">2020s</button></li>
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
				<input type="hidden" name="filter" value="prezzo">
				<ul> 
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="5">Below 5$</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="10">Below 10$</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="20">Below 20$</button></li>
					<li class="dropdown-options"><button type="submit" class="submit-button" name="filterValue" value="30">Below 30$</button></li>
				</ul>
				</form>
			</div>
		</div>
	</div>

	<div id="main-container">
		<%
		Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
		Collection<?> moviesSearched = (Collection<?>) request.getAttribute("moviesSearched");
		if (moviesSearched == null || moviesSearched.isEmpty()) {
		%>
		<p id="failedSearch">Sorry, your search hasn't produced any results :(</p>
		<p id="suggestion">Here is something else you might be interested in: </p>
		<!-- TODO: ADD NEW MOVIES -->
		<%
		} else {
			%>
		<p id="results">Results: </p>
			<%
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
					<input type="hidden" name="add-to-cart" value="<%=movie.getId() %>">
					<button type="submit" id="add-to-cart" class="scale">Add
						to cart</button>
					<p id="movie-price"><%=df.format(movie.getPrice())%>$
					</p>
					<%
					if (isAdmin) {
					%>
					<a id="movie-edit" href="./admin/editMovie.jsp?id=?<%=movie.getId() %>"></a>
					<form action="./MovieDelete" method="POST" id="delete-form">
					<input type="hidden" name="action" value="delete">
					<input type="hidden" name="delete_id" value="<%=movie.getId()%>">
					<button type="submit" id="delete-movie-button"></button>
					</form>
					<%
					}
					%>
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