package by.htp.jwd.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.htp.jwd.bean.Car;
import by.htp.jwd.bean.PriceList;
import by.htp.jwd.dao.CarDAO;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.connection_pool.ConnectionPool;
import by.htp.jwd.dao.connection_pool.ConnectionPoolException;
import by.htp.jwd.dao.connection_pool.ConnectionProvider;

public class CarDAOimpl implements CarDAO {

	static {
		MYSQLDriverLoader.getInstance();
	}

	private final String brand_s = "brand";
	private final String describe_of_brand_s = "describe_of_brand";
	private final String bodywork_s = "bodywork";
	private final String power_s = "power";
	private final String transmission_s = "transmission";
	private final String number_of_doors_s = "number_of_doors";
	private final String year_s = "year";
	private final String photoPath_s = "photoPath";
	private final String photo_s = "photo";
	private final String cost_hour_s = "cost_hour";
	private final String cost_dayr_s = "cost_dayr";
	private final String sale_s = "sale";
	private final String carId_s = "car.Id";

	@Override
	public boolean addCar(Car car) throws DAOException {

		boolean result = true;
		Connection con = null;
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		final String CAR_ADD = "INSERT INTO car (brand, describe_of_brand, bodywork, power, transmission, number_of_doors, year, photo, photoPath  ) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			cp.initPoolData();
			con = cp.takeConnection();
			PreparedStatement preparedStatement = con.prepareStatement(CAR_ADD);

			preparedStatement.setString(1, car.getBrand());
			preparedStatement.setString(2, car.getDescribe_of_brand());
			preparedStatement.setString(3, car.getBodywork());
			preparedStatement.setString(4, car.getPower());
			preparedStatement.setString(5, car.getTransmission());
			preparedStatement.setInt(6, car.getNumber_of_doors());
			preparedStatement.setInt(7, car.getYear());
			preparedStatement.setString(9, car.getPhotoPath());

			InputStream instream = null;

			instream = Files.newInputStream(Paths.get(car.getPhotoPath()));
			preparedStatement.setBinaryStream(8, instream);

			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			result = false;
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} catch (IOException e) {

			e.printStackTrace();
		}

		finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}

		return result;
	}

	@Override
	public List<PriceList> allCar(int numberPage) throws DAOException {

		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		String LIST_NEWS = "SELECT * FROM car LEFT JOIN price_list ON price_list.carId = car.Id ";

		if (numberPage == 1) {
			LIST_NEWS = "SELECT * FROM car LEFT JOIN price_list ON price_list.carId = car.Id LIMIT 0,5";
		}
		if (numberPage > 1) {
			LIST_NEWS = "SELECT * FROM car LEFT JOIN price_list ON price_list.carId = car.Id LIMIT "
					+ ((numberPage * 5) - 5) + ", " + (numberPage * 5);
		}

		ResultSet rs = null;
		List<PriceList> cars = null;
		Connection con = null;
		try {
			cp.initPoolData();
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(LIST_NEWS);
			cars = new ArrayList<PriceList>();

			while (rs.next()) {

				String brand = rs.getString(brand_s);
				String describe_of_brand = rs.getString(describe_of_brand_s);
				String bodywork = rs.getString(bodywork_s);
				String power = rs.getString(power_s);
				String transmission = rs.getString(transmission_s);
				int number_of_doors = rs.getInt(number_of_doors_s);
				int year = rs.getInt(year_s);
				String photoPath = rs.getString(photoPath_s);
				Blob photo = rs.getBlob(photo_s);

				////////////////////////////////////////////
				double cost_hour = rs.getDouble(cost_hour_s);
				double cost_dayr = rs.getDouble(cost_dayr_s);
				double sale = rs.getDouble(sale_s);

				///////////////////////////////////////////
				Car car = new Car(brand, describe_of_brand, bodywork, power, transmission, number_of_doors, year,
						photoPath);
				PriceList priceList = new PriceList(cost_hour, cost_dayr, sale, car);
				cars.add(priceList);

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (NullPointerException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} catch (NullPointerException e) {
				throw new DAOException(e);
			}
		}

		return cars;
	}

	@Override
	public boolean addPriceToCar(PriceList priceList) throws DAOException {

		boolean result = true;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		final String CAR = "SELECT MAX(Id) FROM car";
		final String PRICE_ADD = "INSERT INTO price_list (cost_hour, cost_dayr, sale, carId  ) values (?, ?, ?, ?)";

		try {
			cp.initPoolData();
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(CAR);
			int carId = 0;
			while (rs.next()) {
				carId = rs.getInt("MAX(Id)");
			}

			PreparedStatement preparedStatement = con.prepareStatement(PRICE_ADD);

			preparedStatement.setDouble(1, priceList.getCost_hour());
			preparedStatement.setDouble(2, priceList.getCost_dayr());
			preparedStatement.setDouble(3, priceList.getSale());
			preparedStatement.setInt(4, carId);

			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			result = false;
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}

		return result;
	}

	@Override
	public Integer getNumberOfRows() throws DAOException {

		Integer numOfRows = 0;

		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		// final String LIST_NEWS = "SELECT * FROM car";
		final String LIST_NEWS = "SELECT COUNT(Id) FROM car";
		ResultSet rs = null;
		List<PriceList> cars = null;
		Connection con = null;
		try {
			cp.initPoolData();
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(LIST_NEWS);

			while (rs.next()) {

				numOfRows = rs.getInt("COUNT(Id)");

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (NullPointerException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} catch (NullPointerException e) {
				throw new DAOException(e);
			}
		}

		return numOfRows;
	}

	@Override

	public List<PriceList> lookingCar(String adressStart_s, String dateStart_s, String dateEnd_s, String timeStart_s,
			String timeEnd_s) throws DAOException {
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		String LIST_CARS = "SELECT * FROM car LEFT JOIN price_list ON price_list.carId = car.Id LEFT JOIN oder ON oder.carId = car.Id  WHERE ((oder.date_of_start BETWEEN '"
				+ dateStart_s + "' AND '" + dateEnd_s + "' ) OR (oder.date_of_end BETWEEN '" + dateStart_s + "' AND '"
				+ dateEnd_s + "'))  ";

		ResultSet rs = null;
		List<PriceList> cars = null;
		Connection con = null;
		try {
			cp.initPoolData();
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(LIST_CARS);
			cars = new ArrayList<PriceList>();

			while (rs.next()) {
				int idCar = rs.getInt(carId_s);
				String brand = rs.getString(brand_s);
				String describe_of_brand = rs.getString(describe_of_brand_s);
				String bodywork = rs.getString(bodywork_s);
				String power = rs.getString(power_s);
				String transmission = rs.getString(transmission_s);
				int number_of_doors = rs.getInt(number_of_doors_s);
				int year = rs.getInt(year_s);
				String photoPath = rs.getString(photoPath_s);
				Blob photo = rs.getBlob(photo_s);

				////////////////////////////////////////////
				double cost_hour = rs.getDouble(cost_hour_s);
				double cost_dayr = rs.getDouble(cost_dayr_s);
				double sale = rs.getDouble(sale_s);

				///////////////////////////////////////////
				Car car = new Car(idCar, brand, describe_of_brand, bodywork, power, transmission, number_of_doors, year,
						photoPath);
				// System.out.println(car.toString());
				PriceList priceList = new PriceList(cost_hour, cost_dayr, sale, car);
				cars.add(priceList);

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (NullPointerException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} catch (NullPointerException e) {
				throw new DAOException(e);
			}
		}

		return cars;
	}

	@Override
	public List<PriceList> allCar() throws DAOException {
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		String LIST_NEWS = "SELECT * FROM car LEFT JOIN price_list ON price_list.carId = car.Id ";

		ResultSet rs = null;
		List<PriceList> cars = null;
		Connection con = null;
		try {
			cp.initPoolData();
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery(LIST_NEWS);
			cars = new ArrayList<PriceList>();

			while (rs.next()) {

				int idCar = rs.getInt(carId_s);
				String brand = rs.getString(brand_s);
				String describe_of_brand = rs.getString(describe_of_brand_s);
				String bodywork = rs.getString(bodywork_s);
				String power = rs.getString(power_s);
				String transmission = rs.getString(transmission_s);
				int number_of_doors = rs.getInt(number_of_doors_s);
				int year = rs.getInt(year_s);
				String photoPath = rs.getString(photoPath_s);
				Blob photo = rs.getBlob(photo_s);

				////////////////////////////////////////////
				double cost_hour = rs.getDouble(cost_hour_s);
				double cost_dayr = rs.getDouble(cost_dayr_s);
				double sale = rs.getDouble(sale_s);

				///////////////////////////////////////////
				Car car = new Car(idCar, brand, describe_of_brand, bodywork, power, transmission, number_of_doors, year,
						photoPath);
				PriceList priceList = new PriceList(cost_hour, cost_dayr, sale, car);
				cars.add(priceList);

			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (NullPointerException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			} catch (NullPointerException e) {
				throw new DAOException(e);
			}
		}

		return cars;
	}

}
