package com.neotechlabs.webapp.service;

import java.util.ArrayList;
import java.util.List;

import com.neotechlabs.webapp.todo.Todo;

public class TodoService {
	static List<Todo> todos = new ArrayList<Todo>();

	static {
		todos.add(new Todo("Learn Web Application Development", "Study"));
		todos.add(new Todo("Learn Spring MVC", "Study"));
		todos.add(new Todo("Learn Spring ReST Services", "Study"));
	}
	
	public List<Todo> retrieveTodos() {
		return todos;
	}
	
	public void addNewTodo(Todo todo) {
		todos.add(todo);
	}
	
	public void deleteTodo(Todo todo) {
		todos.remove(todo);
	}
}
