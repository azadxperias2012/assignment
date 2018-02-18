<%@ include file="../common/header.jspf"%>

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
	<c:if test="${errorMessage != null}">
		<div class="alert alert-danger" role="alert">${errorMessage}</div>
	</c:if>

	<form action="/login.do" method="post">
		<div class="form-group">
			<input type="text" class="form-control" name="name"
				placeholder="Username">
		</div>
		<div class="form-group">
			<input type="password" class="form-control" name="password"
				placeholder="Password">
		</div>
		<button type="submit" class="btn btn-primary">Login</button>
	</form>
</div>

<%@ include file="../common/footer.jspf"%>