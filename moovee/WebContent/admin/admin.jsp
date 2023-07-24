<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,model.Movie, java.text.*"%>

<%
request.setAttribute("referer", "/admin/admin.jsp");
Collection<?> movies = (Collection<?>) request.getAttribute("movies");
if (movies == null) {
	request.getServletContext().getRequestDispatcher("/MovieDisplay").forward(request, response);
	return;
}
%>

<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link href="../styles/index.css" type="text/css" rel="stylesheet">
<link href="../styles/search.css" type="text/css" rel="stylesheet">
<link href="../styles/admin.css" type="text/css" rel="stylesheet">
<link rel="icon" href="../images/windowlogo-light.png">
<title>Admin</title>
</head>

<body>

	<%@ include file="../header.jsp"%>

	<div id="main-container">
		<div id="top">
			<h1>CATALOGUE</h1>
			<a href="adminMovieAdd.jsp" id="add"></a>
		</div>
		<%
		DecimalFormat df = new DecimalFormat("#.00");
		Boolean isAdmin = (Boolean) session.getAttribute("isAdmin");
		if (movies != null && movies.size() > 0) {
			Iterator<?> it = movies.iterator();
			while (it.hasNext()) {
				Movie movie = (Movie) it.next();
				if (movie.getQty() >= 0) {
		%>

		<div id="movie-container" class="scale">
			<a href="movie.jsp"><img
				src="../GetLandscapePicture?id=<%=movie.getId()%>"
				onerror="this.src='./images/noimageavailable.jpg'" alt="movie"
				id="movie-poster"></a>
			<p id="movie-title"><%=movie.getTitle()%></p>
			<div id="container-bottom">
					<%
					if (isAdmin) {
					%>
					<a id="movie-edit" href="editMovie.jsp?id=<%=movie.getId()%>"></a>
					<form action="../MovieDelete" method="POST" id="delete-form">
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
	<%@ include file="../footer.jsp"%>
</body>
</html>