package by.htp.jwd.dao;

import by.htp.jwd.dao.impl.CarDAOimpl;
import by.htp.jwd.dao.impl.UserDAOimpl;

public final class DAOProvider {
	private static final DAOProvider instanse = new DAOProvider();
	private final UserDAO userdao = new UserDAOimpl();
	private final CarDAO cardao = new CarDAOimpl();
	
	private DAOProvider() {
	}

	public static DAOProvider getInstance() {
		return instanse;
	}
	
	public UserDAO getUserdao() {
		return userdao;
	}

	public CarDAO getCardao() {
		return cardao;
	}
	
}
