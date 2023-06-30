<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*"%>
<!DOCTYPE html>
<html lang="eng">
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
</head>
<body>

<%@ include file="header.jsp" %>

<h1>Sign Up</h1>

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

<form action="./signup" method="Post">
	<!-- TODO: use ajax to check username availability -->
	Username: <input type="text" name="username"> <br> 
	Name: <input type="text" name="fname"> <br>
	Surname: <input type="text" name="lname"> <br>
	Email: <input type="email" name="email"> <br>
	Password: <input type="password" name="password"> <br>
	Address: <input type="text" name="address"> <br>
	<button type="submit">Sign Up</button> <br>
	<button type="reset">Reset</button>	<br>
</form>

<a href="./login.jsp">Already have an account? Log in here!</a>

</body>
</html>