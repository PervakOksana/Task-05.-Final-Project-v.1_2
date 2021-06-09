package by.htp.jwd.service.impl;

import by.htp.jwd.bean.User;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.DAOProvider;
import by.htp.jwd.dao.UserDAO;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.UserService;


public class UserServiceImpl implements UserService{

	@Override
	public User authorization(String login, String password) throws ServiceException {
		// validation
				if (login == null || "".equals(login)) {
					throw new ServiceException("Wrong login or password");
				}
				DAOProvider proviger = DAOProvider.getInstance();
				UserDAO userDAO = proviger.getUserdao();
System.out.println("UserServiceImpl");
				User user = null;
				try {
					user = userDAO.authorization(login, password);
					
				} catch (DAOException e) {
					throw new ServiceException( e);
				}

				return user;
	}

	@Override
	public boolean registration(User user) throws ServiceException {
		DAOProvider proviger = DAOProvider.getInstance();
		UserDAO userDAO = proviger.getUserdao();
		boolean result = false;
		try {
			result = userDAO.registration(user);
			
		} catch (DAOException e) {
			throw new ServiceException( e);
		}
		return result;
	}

}
