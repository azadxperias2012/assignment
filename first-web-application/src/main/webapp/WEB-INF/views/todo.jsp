<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Todo</title>
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	</head>
	<body>
		<div class="container">
			<div>				
				<c:if test="${name != null}">
					<div class="alert alert-success" role="alert">
			  			Welcome ${name}
					</div>
				</c:if>
				<h1 class="display-4">Your Todos are:</h1>
				<ul class="list-group">
					<c:forEach items="${todos}" var="todo">
						<li class="list-group-item">
							${todo.name}
							<a href="/delete-todo.do?todo=${todo.name}">Delete</a>
						</li>
						
					</c:forEach>
				</ul>
			</div>
			
			<form action="todo.do" method="post">
			  <div class="form-row" style="margin-top:16px;">
			    <div class="col">
			      <input type="text" class="form-control" name="todo" placeholder="Add new todo">
			    </div>
			    <div class="col">
			      <button type="submit" class="btn btn-primary">Add</button>
			    </div>
			  </div>
			</form>
		</div>
	</body>
</html>