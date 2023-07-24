<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang=eng>
<head>
<link rel="icon" href="../images/windowlogo-light.png">
<link href="../styles/forms.css" rel="stylesheet" type="text/css">
<link href="../styles/search.css" rel="stylesheet" type="text/css">
<link href="../styles/cart.css" rel="stylesheet" type="text/css">
<link href="../styles/checkout.css" rel="stylesheet" type="text/css">
<link href="../styles/addMovie.css" rel="stylesheet" type="text/css">
<script src="../scripts/validateForm.js" type="text/javascript"></script>
<meta charset="ISO-8859-1">
<title>Add Movie to Catalogue</title>
</head>
<body>

	<%@ include file="../header.jsp"%>

	<div id="main-container">
		<h3>ADD MOVIE</h3>


		<div class="form-container">
			<form action="../MovieAdd" method="post" enctype="multipart/form-data">
				<input type="hidden" name="action" value="add">
				<p class="label">Title:</p>
				<input type="text" required name="title" class="input"
					onblur="return notEmpty(this)">
				<p class="error-message" id="title-error"></p>
				<p class="label">Regista:</p>
				<input type="text" name="director" class="input"
					onblur="return notEmpty(this)">
				<p class="error-message" id="director-error"></p>
				<p class="label">Genre:</p>
				<input required type="text" name="genre" class="input same-line"
					onblur="return notEmpty(this)">
				<p class="error-message" id="genre-error"></p>
				<div class="same-line-left">
					<p class="label">Price:</p>
					<input required type="number" name="price" id="price"
						class="input  same-line" onblur="return notEmpty(this)">
					<p class="error-message" id="price-error"></p>
				</div>
				<div class="same-line-right">
					<p class="label">Release year:</p>
					<input type="number" name="releaseYear" class="input"
						onblur="return notEmpty(this)">
					<p class="error-message" id="releaseYear-error"></p>
				</div>
				<div class="same-line-left">
					<p class="label">Length:</p>
					<input type="number" name="length" class="input"
						onblur="return notEmpty(this)">
					<p class="error-message" id="length-error"></p>
				</div>
				<div class="same-line-right">
					<p class="label">Quantity:</p>
					<input type="number" name="qty" class="input"
						onblur="return notEmpty(this)">
					<p class="error-message" id="qty-error"></p>
				</div>
				<p class="label">Poster:</p>
				<input type="file" name="poster" class="input"
					onblur="return notEmpty(this)">
				<p class="error-message" id="poster-error"></p>
				<p class="label">Plot:</p>
				<p class="error-message" id="plot-error"></p>
				<input type="text" name="plot" class="input" onblur="return notEmpty(this)">
				<button type="submit" class="submit-button">Add movie</button>
			</form>
		</div>


	</div>

	<%@ include file="../footer.jsp"%>

</body>
</html>