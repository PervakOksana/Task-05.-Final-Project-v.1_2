package by.htp.jwd.command.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import by.htp.jwd.bean.Car;
import by.htp.jwd.command.Command;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;

@MultipartConfig(maxFileSize = 16177215)
public class AddNewCar implements Command {
	
	private final String brand_s = "brand";
	private final String describe_of_brand_s = "describe_of_brand";
	private final String bodywork_s = "bodywork";
	private final String power_s = "power";
	private final String transmission_s = "transmission";
	private final String number_of_doors_s = "number_of_doors";
	private final String year_s = "year";
	private final String photoPath_s = "photoPath";
	private final String path_addpriceview = "Controller?command=addpriceview";
	private final String path_indexview = "Controller?command=indexview";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceProvider provider = ServiceProvider.getInstance();
		CarService carService = provider.getCarServise();

		String brand = request.getParameter(brand_s);
		String describe_of_brand = request.getParameter(describe_of_brand_s);
		String bodywork = request.getParameter(bodywork_s);
		String power = request.getParameter(power_s);
		String transmission = request.getParameter(transmission_s);
		int number_of_doors = Integer.parseInt(request.getParameter(number_of_doors_s));
		int year = Integer.parseInt(request.getParameter(year_s));

		String photoPath = request.getParameter(photoPath_s);

		Car car = new Car(brand, describe_of_brand, bodywork, power, transmission, number_of_doors, year, photoPath);
		System.out.println(car.toString());
		try {
			carService.addCarService(car);
			response.sendRedirect(path_addpriceview);
		} catch (ServiceException e) {
			response.sendRedirect(path_indexview);
		}
	}

}
