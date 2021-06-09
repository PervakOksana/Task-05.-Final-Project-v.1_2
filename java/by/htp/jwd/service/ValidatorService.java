package by.htp.jwd.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ValidatorService {
	
	boolean loginationValidator(HttpServletRequest request,HttpServletResponse response) ;
	boolean registrationValidator(HttpServletRequest request) ;
	boolean dateValidator(HttpServletRequest request) ;

}
