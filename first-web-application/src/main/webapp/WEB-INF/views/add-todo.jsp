<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div class="container">
	Your New Action Item:
	<form action="add-todo.do" method="post">
		<fieldset class="form-group">
			<label>Description :</label> <input type="text" class="form-control"
				name="todo" placeholder="New todo description">
		</fieldset>
		<fieldset class="form-group">
			<label>Category :</label> <input type="text" class="form-control"
				name="category" placeholder="Category">
		</fieldset>
		<button type="submit" class="btn btn-primary">Add</button>
	</form>
</div>

<%@ include file="../common/footer.jspf"%>