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

public class AllCarsView implements Command {

	private final String tPage_s = "tPage";
	private final String noOfPages_s = "noOfPages";
	private final String currentPage_s = "currentPage";
	private final String recordsPerPage_s = "recordsPerPage";
	private final String cars_s = "cars";
	private final String path_all_cars = "/WEB-INF/views/all_cars.jsp";
	private final String path_index_view = "/WEB-INF/views/index_view.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		CarService carService = provider.getCarServise();
		HttpSession session = request.getSession();

		int currentPage = 1;
		int recordsPerPage = 5;
		if (request.getParameter(tPage_s) != null) {

			currentPage = Integer.parseInt(request.getParameter(tPage_s));
		}
		try {

			List<PriceList> cars = carService.allCar(currentPage);

			int rows = carService.getNumberOfRows();

			int nOfPages = rows / recordsPerPage;

			if (nOfPages % recordsPerPage > 0) {

				nOfPages++;
			}

			request.setAttribute(noOfPages_s, nOfPages);
			request.setAttribute(currentPage_s, currentPage);
			request.setAttribute(recordsPerPage_s, recordsPerPage);
			// System.out.println("numOfRows - " + carService.getNumberOfRows());
			request.setAttribute(cars_s, cars);

			RequestDispatcher requestDispather = request.getRequestDispatcher(path_all_cars);
			requestDispather.forward(request, response);
			// session.removeAttribute("messageLog");
			// session.removeAttribute("message");

		} catch (ServiceException e) {

			RequestDispatcher requestDispather = request.getRequestDispatcher(path_index_view);
			requestDispather.forward(request, response);
		}
	}

}
