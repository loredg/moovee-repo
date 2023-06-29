<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*, model.Movie"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link href="styles/index.css" type="text/css" rel="stylesheet">
<link rel="icon" href="images/windowlogo-light.png">
<title>moovee</title>
</head>
<body>

	<!-- TODO: add alert to confirm registration. Get attribute hasRegistered -->

	<%@ include file="header.jsp" %>

	<h3 id="whats-new">What's new this week</h3>
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

	<div id="new-movies"><%=movie.getTitle()%>,&nbsp
	</div>

	<%
	}
	}
	%>

	<h3>CATALOGUE</h3>

	<%
	request.setAttribute("referer", "/index.jsp");
	Collection<?> movies = (Collection<?>) request.getAttribute("movies");
	if (movies == null) {
		request.getRequestDispatcher("./MovieDisplay").forward(request, response);
		return;
	}
	if (movies != null && movies.size() > 0) {
		Iterator<?> it = movies.iterator();
		while (it.hasNext()) {
			Movie movie = (Movie) it.next();
			if (movie.getQty() >= 0) {
	%>

	<div id="movie">
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
	</div>
	<%
				}
	}
	}
	%>

	<%@ include file="footer.jsp" %>

</body>
</html>