package by.htp.jwd.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.jwd.command.Command;

public class AddNewCarView implements Command{

	private final String path_add_car = "/WEB-INF/views/add_car.jsp";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher requestDispather = request.getRequestDispatcher(path_add_car);
		requestDispather.forward(request, response);
	}

}
