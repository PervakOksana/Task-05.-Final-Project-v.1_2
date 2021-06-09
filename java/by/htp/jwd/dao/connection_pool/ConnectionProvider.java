package by.htp.jwd.dao.connection_pool;

public class ConnectionProvider {

	private static final ConnectionProvider instance = new ConnectionProvider();

	private ConnectionProvider() {
	}

	private final ConnectionPool connectionPool = new ConnectionPool();

	public static ConnectionProvider getInstance() {
		return instance;
	}

	public ConnectionPool getConnectionPool() {
		return connectionPool;
	}

	public void initConnectionPool() {
		try {
			connectionPool.initPoolData();
		} catch (ConnectionPoolException e) {
		}
	}

	public void disposeConnectionPool() {

		connectionPool.dispose();

	}

}
