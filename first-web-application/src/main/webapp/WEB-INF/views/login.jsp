<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login Page</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<c:if test="${errorMessage != null}">
				<div class="alert alert-danger" role="alert">
		  			${errorMessage}
				</div>
			</c:if>
			<div class="jumbotron">
				<h1 class="display-4">Hello, world!</h1>
			</div>
			
			<form action="/login.do" method="post">
			  <div class="form-group">
			  	<label for="name">Username</label>
			    <input id="name" type="text" class="form-control" name="name" placeholder="name">			    
			  </div>
			  <div class="form-group">
			  	<label for="password">Password</label>
			    <input id="password" type="password" class="form-control" name="password" placeholder="password">
			  </div>
			  <button type="submit" class="btn btn-primary">Login</button>
			</form>
		</div>
	</body>
</html>