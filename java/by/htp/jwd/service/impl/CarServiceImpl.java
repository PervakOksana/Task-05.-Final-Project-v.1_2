package by.htp.jwd.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.htp.jwd.bean.Car;
import by.htp.jwd.bean.PriceList;
import by.htp.jwd.dao.CarDAO;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.DAOProvider;
import by.htp.jwd.service.CarService;
import by.htp.jwd.service.ServiceException;

public class CarServiceImpl implements CarService {

	@Override
	public boolean addCarService(Car car) throws ServiceException {

		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		boolean result = false;

		try {
			result = carDAO.addCar(car);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public List<PriceList> allCar(int numberPage) throws ServiceException {

		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		List<PriceList> cars = null;

		try {
			cars = carDAO.allCar(numberPage);

		} catch (DAOException e) {

			throw new ServiceException(e);

		}
		return cars;
	}

	@Override
	public boolean addPriceToCarService(PriceList priceList) throws ServiceException {

		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		boolean result = false;

		try {
			result = carDAO.addPriceToCar(priceList);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return result;
	}

	@Override
	public Integer getNumberOfRows() throws ServiceException {

		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		Integer number = 0;
		try {
			number = carDAO.getNumberOfRows();

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return number;
	}

	@Override
	public List<PriceList> lookingCar(String adressStart_s, String dateStart_s, String dateEnd_s, String timeStart_s,
			String timeEnd_s) throws ServiceException {

		DAOProvider proviger = DAOProvider.getInstance();
		CarDAO carDAO = proviger.getCardao();

		List<PriceList> cars = null;
		List<PriceList> carsAll = null;
		List<PriceList> carsFree = new ArrayList<PriceList>();
		int count = 0;

		try {
			cars = carDAO.lookingCar(adressStart_s, dateStart_s, dateEnd_s, timeStart_s, timeEnd_s);
			carsAll = carDAO.allCar();
			for (int i = 0; i < carsAll.size(); i++) {
				for (int j = 0; j < cars.size(); j++) {

					if (carsAll.get(i).getCar().getId() == cars.get(j).getCar().getId()) {
						count++;
						
					}

				}

				if (count == 0) {
					carsFree.add(carsAll.get(i));
				}
				count = 0;
			}

		} catch (DAOException e) {

			throw new ServiceException(e);

		}
		return carsFree;
	}

}
