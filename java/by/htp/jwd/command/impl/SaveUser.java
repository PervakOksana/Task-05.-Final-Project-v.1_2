package by.htp.jwd.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jwd.bean.User;
import by.htp.jwd.command.Command;
import by.htp.jwd.service.ServiceException;
import by.htp.jwd.service.ServiceProvider;
import by.htp.jwd.service.UserService;


public class SaveUser implements Command {

	private final String login_s = "login";
	private final String password_s = "password";
	private final String name_s = "name";
	private final String surname_s = "surname";
	private final String user_s = "user";
	private final String auth_s = "auth";
	private final String role_s = "role";
	private final String message_s = "message";
	private final String registration_s = "registration";
	private final String path_index_view = "Controller?command=indexview";
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String login;
		String password;
		String name;
		String surname;

		login = request.getParameter(login_s);
		password = request.getParameter(password_s);
		name = request.getParameter(name_s);
		surname = request.getParameter(surname_s);

		ServiceProvider provider = ServiceProvider.getInstance();
		UserService userService = provider.getUserServise();
		//ValidatorService validatorService = provider.getValidatorService();

		try {
//			if (!validatorService.registrationValidator(request)) {
//				response.sendRedirect("Controller?command=gotoindexpage&message = errorNew");
//				return;
//			}
			User user = new User(login, password, name, user_s, surname);
			userService.registration(user);
			System.out.println(user.toString());
			if (user == null) {
				response.sendRedirect(path_index_view);
			}
			if (user != null) {

				HttpSession session = request.getSession(true);
				session.setAttribute(auth_s, true);
				session.setAttribute(role_s, user.getRole());
				session.setAttribute(message_s, registration_s);
				response.sendRedirect(path_index_view);

			}
		} catch (ServiceException e) {
			response.sendRedirect(path_index_view);
		}

	}
}