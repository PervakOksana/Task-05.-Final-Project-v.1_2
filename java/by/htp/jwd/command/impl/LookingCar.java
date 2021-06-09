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
import by.htp.jwd.service.ValidatorService;

public class LookingCar implements Command {

	private final String adressStart = "adressStart";
	private final String adressEnd = "adressEnd";
	private final String dateStart = "dateStart";
	private final String dateEnd = "dateEnd";
	private final String timeStart = "timeStart";
	private final String timeEnd = "timeEnd";
	private final String cars_s = "cars";
	private final String notice_s = "notice";
	private final String error_s = "error";
	private final String path_looking_cars = "/WEB-INF/views/looking_cars.jsp";
	private final String path_index_view = "/WEB-INF/views/index_view.jsp";
	private final String path_choose_car_view = "/WEB-INF/views/choose_car_view.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String adressStart_s = request.getParameter(adressStart);
		String adressEnd_s = request.getParameter(adressEnd);
		String dateStart_s = request.getParameter(dateStart);
		String dateEnd_s = request.getParameter(dateEnd);
		String timeStart_s = request.getParameter(timeStart);
		String timeEnd_s = request.getParameter(timeEnd);
		
		ServiceProvider provider = ServiceProvider.getInstance();
		CarService carService = provider.getCarServise();
		ValidatorService validatorService = provider.getValidatorService();
		HttpSession session = request.getSession();
		//System.out.println("LookingCar - Command");

//		if (!validatorService.loginationValidator(request, response)) {
//			response.sendRedirect(path_index_view);
//			return;
//		}
		try {
			if (!validatorService.dateValidator(request)) {
				session.setAttribute(notice_s, error_s);
				//System.out.println("!validatorService.dateValidator(request)");
				RequestDispatcher requestDispather = request.getRequestDispatcher(path_choose_car_view);
				requestDispather.forward(request, response);
                
				return;
			}

			List<PriceList> cars = carService.lookingCar( adressStart_s, dateStart_s, dateEnd_s, timeStart_s, timeEnd_s);
	
			request.setAttribute(cars_s, cars);

			RequestDispatcher requestDispather = request.getRequestDispatcher(path_looking_cars);
			requestDispather.forward(request, response);
			// session.removeAttribute("messageLog");
			// session.removeAttribute("message");

		} catch (ServiceException e) {

			RequestDispatcher requestDispather = request.getRequestDispatcher(path_index_view);
			requestDispather.forward(request, response);
		}
		
		

	}

}
