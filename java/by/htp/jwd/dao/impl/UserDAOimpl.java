package by.htp.jwd.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.htp.jwd.bean.User;
import by.htp.jwd.dao.DAOException;
import by.htp.jwd.dao.UserDAO;
import by.htp.jwd.dao.connection_pool.ConnectionPool;
import by.htp.jwd.dao.connection_pool.ConnectionPoolException;
import by.htp.jwd.dao.connection_pool.ConnectionProvider;

public class UserDAOimpl implements UserDAO {

	static {
		MYSQLDriverLoader.getInstance();
	}
	private final String logine_s = "logine";
	private final String passworde_s = "passworde";
	private final String name_s = "name";
	private final String surname_s = "surname";
	private final String role_s = "role";
	

	
	@Override
	public boolean registration(User user) throws DAOException {
		boolean result = true;
		Connection con = null;
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();

		try {
			cp.initPoolData();
			con = cp.takeConnection();
			st = con.createStatement();

			String command = "INSERT INTO users (login, password,name ,surname, role) VALUES ('" + user.getLogin()
					+ "','" + user.getPassword() + "','" + user.getName() + "','" + user.getSurname() + "','"
					+ user.getRole() + "')";
			st.executeUpdate(command);

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}

		return result;

	}

	@Override
	public User authorization(String login, String password) throws DAOException {

		ResultSet rs = null;
		User user = null;
		Connection con = null;
		Statement st = null;
		ConnectionProvider provider = ConnectionProvider.getInstance();
		ConnectionPool cp = provider.getConnectionPool();
		
		try {
			cp.initPoolData();
			con = cp.takeConnection();
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user WHERE logine = '" + login + "' AND passworde ='" + password + "'");
			
			while (rs.next()) {

				login = rs.getString(logine_s);
				password = rs.getString(passworde_s);
				String name = rs.getString(name_s);
				String surname = rs.getString(surname_s);
				String role = rs.getString(role_s);

				user = new User(login, password, surname, role, name);
				
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} catch (NullPointerException e) {
			throw new DAOException(e);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			} catch (NullPointerException e) {
				throw new DAOException(e);
			}
		}

		return user;
	}

}
