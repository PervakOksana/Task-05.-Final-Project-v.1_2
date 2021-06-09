package by.htp.jwd.service;

import by.htp.jwd.bean.User;

public interface UserService {
	
	User authorization(String login, String password) throws ServiceException;

	public boolean registration(User user) throws ServiceException;

}
