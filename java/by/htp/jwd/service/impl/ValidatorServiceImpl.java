package by.htp.jwd.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.jwd.service.ValidatorService;

public class ValidatorServiceImpl implements ValidatorService {

	private final String auth_s = "auth";
	private final String password_s = "password";
	private final String dateStart_s = "dateStart";
	private final String password_pattern_s = "(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}";
	private final String dateStart_pattern_s = "((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])";
	
	@Override
	public boolean loginationValidator(HttpServletRequest request, HttpServletResponse response) {
		boolean result = true;
		HttpSession session = request.getSession();
		if (session == null) {
			result = false;

		}
		Boolean iaAuth = (Boolean) session.getAttribute(auth_s);
		if (iaAuth == null || !iaAuth) {
			result = false;

		}
		return result;
	}

	@Override
	public boolean registrationValidator(HttpServletRequest request) {
		String password;

		password = request.getParameter(password_s);
		Pattern pattern = Pattern.compile(password_pattern_s);
		Matcher matcher = pattern.matcher(password);
		matcher.lookingAt();
		return matcher.lookingAt();
	}

	@Override
	public boolean dateValidator(HttpServletRequest request) {
		String date;

		date = request.getParameter(dateStart_s);
  		Pattern pattern = Pattern.compile(dateStart_pattern_s);
		Matcher matcher = pattern.matcher(date);
		matcher.lookingAt();
		return matcher.lookingAt();
	}

}
