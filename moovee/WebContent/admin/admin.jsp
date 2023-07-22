<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*,model.Movie"%>

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
<link href="../styles/admin.css" type="text/css" rel="stylesheet" />
<link rel="icon" href="../images/windowlogo-light.png">
<title>Admin</title>
</head>

<body>

	<%@ include file="../header.jsp"%>

	<h1>CATALOGUE</h1>
	<%
	if (movies != null && movies.size() > 0) {
		Iterator<?> it = movies.iterator();
		while (it.hasNext()) {
			Movie movie = (Movie) it.next();
			if (movie.getQty() >= 0) {
	%>

	<div id="movie">
		<img src="../GetLandscapePicture?id=<%=movie.getId()%>"
			onerror="this.src='./images/noimageavailable.jpg'"
			style="width: 150px; height: 200px" alt="movie">
		<div id="movie-deets">
			<label id="movie-title"><%=movie.getTitle()%></label>
		</div>
	</div>
	<%
	}
	}
	}
	%>


	<div id="add-movie">
		<h1>ADD MOVIE</h1>
		<form action="../MovieAdd" enctype="multipart/form-data" method="POST">
			<input type="hidden" name="action" value="add"> Titolo: <input
				type="text" name="title" required> <br> <label
				for="director">Regista:</label> <input type="text" name="director"
				required> <br> <label for="genre">Genere:</label> <input
				type="text" name="genre" required> <br> <label
				for="length">Durata in minuti:</label> <input type="number"
				name="length" required> <br> <label for="release_year">Anno
				di pubblicazione:</label> <input type="number" name="release_year" required>
			<br> <label for="price">Prezzo di vendita:</label> <input
				type="number" name="price" required> <br> <label
				for="qty">Copie disponibili alla vendita:</label> <input type="text"
				name="qty" required><br>
			Copertina: <input type="file" name="poster"> <br>
			<button type="submit">Aggiungi</button>
			<button type="reset">Azzera</button>
		</form>
	</div>

	<div id="delete-movie">
		<h1>DELETE MOVIE</h1>
		<form action="../MovieDelete" method="POST">
			<input type="hidden" name="action" value="delete"> <label
				for="id">Inserire l'ID del film da eliminare:</label> <input
				type="text" name="delete_id" required> <br>
			<button type="submit">Rimuovi</button>
			<button type="reset">Azzera</button>
		</form>
	</div>

	<%if(movies!= null && movies.size() > 0) {
		Iterator<?> it = movies.iterator();
		%>
	<div id="addLandscapePoster">
		<h1>ADD LANDSCAPE POSTER</h1>
		<form action="../AddLandscapePoster" method="post"
			enctype="multipart/form-data">
			<select name="id">
			<%
			while(it.hasNext()) {
			Movie movie = (Movie) it.next();
			%>
				<option value="<%=movie.getId()%>"><%=movie.getTitle()%></option>
				<%
				}
				%>
			
			</select>
			Copertina orizzontale: <input type="file" name="landscapePoster">
			<button type="submit">Add poster</button>
		</form>
	</div>
	<%	
			}
	
	%>
	

	<%@ include file="../footer.jsp"%>
	</body>
</html>