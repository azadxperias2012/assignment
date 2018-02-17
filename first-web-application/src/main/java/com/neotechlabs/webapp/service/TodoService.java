package com.neotechlabs.webapp.service;

import java.util.ArrayList;
import java.util.List;

import com.neotechlabs.webapp.todo.Todo;

public class TodoService {
	static List<Todo> todos = new ArrayList<Todo>();

	static {
		todos.add(new Todo("Learn Web Application Development"));
		todos.add(new Todo("Learn Spring MVC"));
		todos.add(new Todo("Learn Spring ReST Services"));
	}
	
	public List<Todo> retrieveTodos() {
		return todos;
	}
}
