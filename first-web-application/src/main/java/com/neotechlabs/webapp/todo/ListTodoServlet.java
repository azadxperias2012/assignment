package com.neotechlabs.webapp.todo;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.neotechlabs.webapp.service.TodoService;

@WebServlet(urlPatterns = "/todo.do")
public class ListTodoServlet extends HttpServlet {
	TodoService todoService = new TodoService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("todos", todoService.retrieveTodos());
		request.getRequestDispatcher("/WEB-INF/views/todo.jsp")
			.forward(request, response);
	}
}
