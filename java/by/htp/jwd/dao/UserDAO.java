package by.htp.jwd.dao;

import by.htp.jwd.bean.User;

public interface UserDAO {

	boolean registration (User user) throws DAOException;
	User authorization(String login, String password) throws DAOException;
}
