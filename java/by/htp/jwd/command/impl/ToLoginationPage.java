package by.htp.jwd.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.jwd.command.Command;

public class ToLoginationPage implements Command{
	
	private final String path_logination = "/WEB-INF/views/logination.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher requestDispather = request.getRequestDispatcher(path_logination);
		requestDispather.forward(request, response);
	}
	
	

}
