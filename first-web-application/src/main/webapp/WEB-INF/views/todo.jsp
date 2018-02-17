<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Todo</title>		
		<link rel="stylesheet" href="webjars/bootstrap/3.3.6/css/bootstrap.min.css">
		
		<style>
			.footer {
				position: absolute;
				bottom: 0;
				width: 100%;
				height: 60px;
				background-color: #f5f5f5;
			}
		</style>
	</head>
	<body>
		
		<nav class="navbar navbar-default">
			<a href="/" class="navbar-brand">Brand</a>
			
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="/todo.do">Todos</a></li>
				<li><a href="http://www.in28minutes.com">In28minutes</a></li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/login.do">Login</a></li>
			</ul>
		</nav>
	
		<div class="container">
			<h1>Welcome ${name}</h1>

			Your Todos are
			<ol>
				<c:forEach items="${todos}" var="todo">
					<li>${todo.name}&nbsp;<a 
						href="/delete-todo.do?todo=${todo.name}">Delete</a>
					</li>						
				</c:forEach>
			</ol>
			
			<form action="add-todo.do" method="post">
			  <div style="margin-top:16px;">
			  	<div class="form-group">
			  		<label for="newTodo">New Todo :</label>
			    	<input id="newTodo" type="text" class="form-control" name="todo" placeholder="Add new todo">			    
			  	</div>
		      <button type="submit" class="btn btn-primary">Add</button>
			  </div>
			</form>
		</div>
		
		<footer class="footer">
			<div>Footer Content</div>
		</footer>
		
		<!-- jQuery library -->
		<script src="webjars/jquery/1.9.1/jquery.min.js"></script>		
		<!-- Latest compiled JavaScript -->
		<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	</body>
</html>