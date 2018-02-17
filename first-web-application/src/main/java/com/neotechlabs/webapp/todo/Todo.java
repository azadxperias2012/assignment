package com.neotechlabs.webapp.todo;

public class Todo {
	private String name;

	public Todo(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Todo [name=%s]", name);
	}
}
