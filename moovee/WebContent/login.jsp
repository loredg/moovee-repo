<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<link rel="icon" href="images/windowlogo-light.png">
<title>Log In</title>
</head>
<body>

	<%@ include file="header.jsp" %>

	<h1>Log In</h1>

	<br>

	<%
	Collection<?> errors = (Collection<?>)request.getAttribute("errors");
	if(errors != null && errors.size() > 0) {
		Iterator<?> it = errors.iterator();
		while(it.hasNext()) {
			String error = (String) it.next();
%>

	<%=error%>
	<br>

	<%
		}
	}
%>

	<form action="./login" method="POST">
		Email: <input type="email" name="email"> <br> Password: <input
			type="password" name="password"> <br>
		<button type="submit">Log In</button>
	</form>

	<a href="./signup.jsp">You don't have an account? Register here!</a>

</body>
</html>