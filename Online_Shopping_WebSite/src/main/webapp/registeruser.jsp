<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
	crossorigin="anonymous"></script>
</head>
<body>

	<jsp:useBean id="objUser" type="org.fi.shopping.pojo.UserInfo"
		scope="session" />

	<%
	Connection connection = (Connection) application.getAttribute("golbalConnection");
	try (PreparedStatement psNewUser = connection.prepareStatement("insert into users_0013 values(?,?,?,?)");

	)

	{
		psNewUser.setString(1, objUser.getUserName());
		psNewUser.setString(2, objUser.getPassword());
		psNewUser.setString(3, objUser.getName());
		psNewUser.setString(4, objUser.getEmail());

		psNewUser.executeUpdate();

	}
	%>
	<div class="container mt-6 p-5 text-center">
		<h1 class="text-success ">You have been registered.</h1>
		<br> <a class="btn btn-primary  text-dark" href='loginPage.html'>Login</a>
	</div>





</body>
</html>