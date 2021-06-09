package by.htp.jwd.service;

import java.util.List;

import by.htp.jwd.bean.Car;
import by.htp.jwd.bean.PriceList;


public interface CarService {

	public boolean addCarService(Car car) throws ServiceException;

	public boolean addPriceToCarService(PriceList priceList) throws ServiceException;

	public List<PriceList> allCar(int numberPage) throws ServiceException;

	public List<PriceList> lookingCar(String adressStart_s, String dateStart_s, String dateEnd_s,
			String timeStart_s, String timeEnd_s) throws ServiceException;

	public Integer getNumberOfRows() throws ServiceException;
	
	
}
