package by.htp.jwd.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jwd.bean.User;
import by.htp.jwd.command.Command;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.UserService;


public class Logination implements Command {

	private final String login_s = "login";
	private final String password_s = "password";
	private final String messageLog_s = "messageLog";
	private final String errorLog_s = "errorLog";
	private final String auth_s = "auth";
	private final String role_s = "role";
	private final String message_s = "message";
	private final String path_choosecarview  = "Controller?command=choosecarview";
	private final String path_indexview = "Controller?command=indexview";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login;
		String password;

		login = request.getParameter(login_s);
		password = request.getParameter(password_s);

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserServise();
		HttpSession session = request.getSession();
		
		User user = null;

		try {
			user = userService.authorization(login, password);
			if (user == null) {
				
				session.setAttribute(messageLog_s, errorLog_s);
				response.sendRedirect(path_indexview);
				

			} else {
				
				session.setAttribute(auth_s, true);
				session.setAttribute(role_s, user.getRole());
				session.setAttribute(message_s, user.getName());
				response.sendRedirect(path_choosecarview);
			}
		} catch (ServiceException e) {

			response.sendRedirect(path_indexview);
		}
		
		
	}

}
