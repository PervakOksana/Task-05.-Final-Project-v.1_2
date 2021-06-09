package by.htp.jwd.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.jwd.command.Command;

public class ChooseCarView implements Command {

	private final String path_choose_car_view = "/WEB-INF/views/choose_car_view.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher requestDispather = request.getRequestDispatcher(path_choose_car_view);
		requestDispather.forward(request, response);
		
	}

}
