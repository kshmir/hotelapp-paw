package ar.edu.itba.it.paw.db.managers;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

import ar.edu.itba.it.paw.db.ConnectionManager;

public class PostgreConnectionManager implements ConnectionManager {

	private final String connectionString;

	final Driver driver = new org.postgresql.Driver();

	public PostgreConnectionManager(final String connectionString) {
		this.connectionString = connectionString;
	}

	public PostgreConnectionManager() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.connectionString = "￼￼jdbc:postgresql://localhost:5432/hotelapp";
	}

	public Connection getConnection() throws SQLException {
		final Properties props = new Properties();
		props.setProperty("user", "cris");
		props.setProperty("password", "holahola");
		return this.driver.connect(this.connectionString, props);
	}
}
