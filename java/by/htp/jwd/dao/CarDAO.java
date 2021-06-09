package by.htp.jwd.dao;

import java.util.List;

import by.htp.jwd.bean.Car;
import by.htp.jwd.bean.PriceList;

public interface CarDAO {
	
	public boolean addCar (Car car) throws DAOException;
	public boolean addPriceToCar (PriceList priceList) throws DAOException;
	public List<PriceList> allCar(int numberPage) throws DAOException;
	public List<PriceList> allCar() throws DAOException;
	public List<PriceList> lookingCar( String adressStart_s, String dateStart_s, String dateEnd_s,String timeStart_s,String timeEnd_s) throws DAOException;
	public Integer getNumberOfRows() throws DAOException;
}
