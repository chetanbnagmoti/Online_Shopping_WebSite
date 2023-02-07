<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review User Page</title>
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
	<jsp:useBean id="objUser" class="org.fi.shopping.pojo.UserInfo"
		scope="session"></jsp:useBean>

	<jsp:setProperty property="*" name="objUser" />

	<div class="container bg-light p-5 border border-1">
		
		<div class="container  mt-6 p-5 ">
			<div class=" mb-4 p-3">
				<h1 class="text-center  ">User Review Page</h1>
				<div class=" container text-center mt-6 p-5">
					<a class="btn btn-primary text-dark" href='newuser.html'>Back</a>
						<a class="btn btn-primary text-dark" href='loginPage.html'>Cancel</a>
							<a class="btn btn-primary text-dark" href='registeruser.jsp'>Register</a>&nbsp;&nbsp;&nbsp; 
				</div>
				<div class="row  justify-content-center ">
					<div class="col-6 ">

						<table class="table table-bordered table-primary table-hover">
							<tr>
								<td>User Name</td>
								<td><jsp:getProperty property="userName" name="objUser" /></td>
							</tr>
							<tr>
								<td>Password</td>
								<td><jsp:getProperty property="password" name="objUser" /></td>
							</tr>
							<tr>
								<td>Name</td>
								<td><jsp:getProperty property="name" name="objUser" /></td>
							</tr>
							<tr>
								<td>Email</td>
								<td><jsp:getProperty property="email" name="objUser" /></td>
							</tr>

						</table>
						


					</div>







				</div>

			</div>

		</div>



	</div>



</body>
</html>