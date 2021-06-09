package by.htp.jwd.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jwd.bean.PriceList;
import by.htp.jwd.command.Command;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;

public class IndexView implements Command {
	
	private final String cars_s = "cars";
	private final String path_index_view = "/WEB-INF/views/index_view.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		CarService carService = provider.getCarServise();
		HttpSession session = request.getSession();
		try {
			
			List<PriceList> cars = carService.allCar(1);	
			//System.out.println("numOfRows - " + carService.getNumberOfRows());
			request.setAttribute(cars_s, cars);

			RequestDispatcher requestDispather = request.getRequestDispatcher(path_index_view);
			requestDispather.forward(request, response);
			// session.removeAttribute("messageLog");
			// session.removeAttribute("message");

		} catch (ServiceException e) {
			
			RequestDispatcher requestDispather = request.getRequestDispatcher(path_index_view);
			requestDispather.forward(request, response);
		}
	}

}
