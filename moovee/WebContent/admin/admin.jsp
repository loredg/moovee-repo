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
<link href="admin.css" type="text/css" rel="stylesheet"/>
<title>Admin</title>
</head>

<body>

<h1>CATALOGUE</h1>

<%
if (movies != null && movies.size() > 0) {
	Iterator<?> it = movies.iterator();
	while (it.hasNext()) {
		Movie movie = (Movie) it.next();
%>

<img src="../GetPicture?id=<%=movie.getId()%>"
	onerror="this.src='./images/noimageavailable.jpg'" style="width:100px;height:200px" alt="picture not available.">
<%=movie.getTitle()%>
<br>
<%
		}
	}
%>

<h1>ADD MOVIE</h1>
	<form action="../MovieAdd" enctype="multipart/form-data" method="POST">
		<input type="hidden" name="action" value="add">
		Titolo: <input type="text" name="title" required> <br> <label
			for="director">Regista:</label> <input type="text" name="director"
			required> <br> <label for="genre">Genere:</label> <input
			type="text" name="genre" required> <br> <label
			for="length">Durata in minuti:</label> <input type="number"
			name="length" required> <br> <label for="release_year">Anno
			di pubblicazione:</label> <input type="number" name="release_year" required>
		<br> <label for="price">Prezzo di vendita:</label> <input
			type="number" name="price" required> <br> <label
			for="qty">Copie disponibili alla vendita:</label> <input type="text"
			name="qty" required> <br> Disponibile come: <br>
		Blu ray: <input type="checkbox" name="blurayorDVD" value="Blu ray">
		<br> DVD:<input type="checkbox" name="blurayorDVD" value="DVD"><br>
		Copertina: <input type="file" name="poster"> <br>
		<button type="submit">Aggiungi</button>
		<button type="reset">Azzera</button>
	</form>
	<br>

	<h1>DELETE MOVIE</h1>
	<form action="../MovieDelete" method="POST">
		<input type="hidden" name="action" value="delete"> <label
			for="id">Inserire l'ID del film da eliminare:</label> <input
			type="text" name="delete_id" required> <br>
		<button type="submit">Rimuovi</button>
		<button type="reset">Azzera</button>
	</form>
	<br>
</body>
</html>