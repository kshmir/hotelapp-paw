package ar.edu.itba.it.paw.db;

import java.sql.Connection;

import ar.edu.itba.it.paw.db.managers.InMemorySQLiteConnectionManager;
import ar.edu.itba.it.paw.db.managers.PostgreConnectionManager;

/**
 * Provides with a production and a test connection dispatcher
 * 
 * @author cris
 */
public class ConnectionDispatcher {

	private ConnectionManager manager;

	private ConnectionDispatcher() {

	}

	/**
	 * Get a connection dispatcher for our production environment
	 * 
	 * @return ConnectionDispatcher for production
	 */
	public static ConnectionDispatcher getDispatcher() {
		final ConnectionDispatcher myDispatcher = new ConnectionDispatcher();
		myDispatcher.manager = new PostgreConnectionManager();
		return myDispatcher;
	}

	/**
	 * Get a connection dispatcher for our test environment
	 * 
	 * @return ConnectionDispatcher for test
	 */
	public static ConnectionDispatcher getTestDispatcher() {
		final ConnectionDispatcher myDispatcher = new ConnectionDispatcher();
		myDispatcher.manager = InMemorySQLiteConnectionManager
				.getConnectionManager();

		return myDispatcher;
	}

	/**
	 * Generic getConnection
	 * 
	 * @return The actual connection provided by a ConnectionManager
	 */
	public Connection getConnection() throws Exception {

		return this.manager.getConnection();
	}
}
