package by.htp.jwd.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.jwd.bean.PriceList;
import by.htp.jwd.command.Command;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;

public class AddPrice implements Command{

	private final String cost_hour_s = "cost_hour";
	private final String cost_dayr_s = "cost_dayr";
	private final String sale_s = "sale";
	private final String path_indexview = "Controller?command=indexview";
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		ServiceProvider provider = ServiceProvider.getInstance();
		CarService carService = provider.getCarServise();

		double cost_hour = Double.parseDouble(request.getParameter(cost_hour_s));
		double cost_dayr = Double.parseDouble(request.getParameter(cost_dayr_s));
		double sale = Double.parseDouble(request.getParameter(sale_s));

		PriceList priceList = new PriceList(cost_hour, cost_dayr, sale);
		
		try {
			carService.addPriceToCarService(priceList);
			response.sendRedirect(path_indexview);
		} catch (ServiceException e) {
			response.sendRedirect(path_indexview);
		}
	}

}
