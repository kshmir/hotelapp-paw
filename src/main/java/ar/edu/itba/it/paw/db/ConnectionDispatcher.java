package ar.edu.itba.it.paw.db;

import java.sql.Connection;

import ar.edu.itba.it.paw.db.managers.InMemorySQLiteConnectionManager;
import ar.edu.itba.it.paw.db.managers.PostgreConnectionManager;

public class ConnectionDispatcher {

	private ConnectionManager manager;

	private ConnectionDispatcher() {

	}

	public static ConnectionDispatcher getDispatcher() {
		final ConnectionDispatcher myDispatcher = new ConnectionDispatcher();
		myDispatcher.manager = new PostgreConnectionManager();
		return myDispatcher;
	}

	public static ConnectionDispatcher getTestDispatcher() {
		final ConnectionDispatcher myDispatcher = new ConnectionDispatcher();
		myDispatcher.manager = InMemorySQLiteConnectionManager
				.getConnectionManager();

		return myDispatcher;
	}

	public Connection getConnection() throws Exception {

		return this.manager.getConnection();
	}
}
