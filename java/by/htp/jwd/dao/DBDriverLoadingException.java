package by.htp.jwd.dao;

public class DBDriverLoadingException extends RuntimeException{
	
	private static final long serialVersionUID = 7413825432151596771L;
	
	public DBDriverLoadingException() {
		super();
	}
	public DBDriverLoadingException(String message) {
		super(message);
	}
	public DBDriverLoadingException(Exception e) {
		super(e);
	}
	public DBDriverLoadingException(String message, Exception e) {
		super(message, e);
	}
}
