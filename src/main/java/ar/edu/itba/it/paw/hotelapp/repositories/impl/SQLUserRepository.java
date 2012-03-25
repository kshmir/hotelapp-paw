package ar.edu.itba.it.paw.hotelapp.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ar.edu.itba.it.paw.db.ConnectionDispatcher;
import ar.edu.itba.it.paw.hotelapp.model.api.User;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleUser;
import ar.edu.itba.it.paw.hotelapp.repositories.api.UserRepository;

public class SQLUserRepository implements UserRepository {

	private ConnectionDispatcher dispatcher;

	public SQLUserRepository() {
		this(null);
	}

	public SQLUserRepository(final ConnectionDispatcher connectionDispatcher) {
		if (connectionDispatcher == null) {
			this.dispatcher = ConnectionDispatcher.getDispatcher();
		} else {
			this.dispatcher = connectionDispatcher;
		}
	}

	private User fetchUser(final ResultSet set, final int id)
			throws SQLException {
		final String name = set.getString("username");
		final String _password = set.getString("password");
		final String email = set.getString("email");

		final User user = new SimpleUser(name, _password, email);

		user.setId(id);

		return user;
	}

	public User getUserById(final int id) {
		User user = null;
		Connection conn = null;
		try {
			conn = this.dispatcher.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM users WHERE id = ?");

			statement.setInt(1, id);

			statement.execute();
			final ResultSet set = statement.getResultSet();

			while (set.next() && !set.isAfterLast()) {
				user = this.fetchUser(set, id);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
		}
		return user;
	}

	public User getUser(final String userName) {
		return this.getUser(userName, null);
	}

	public User getUser(final String userName, final String password) {
		User user = null;
		Connection conn = null;
		try {
			conn = this.dispatcher.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM users WHERE username = ?"
							+ ((password == null) ? "" : "AND password = ?"));

			statement.setString(1, userName);

			if (password != null) {
				statement.setString(2, password);
			}

			statement.execute();
			final ResultSet set = statement.getResultSet();

			while (set.next() && !set.isAfterLast()) {
				final int id = set.getInt("id");
				user = this.fetchUser(set, id);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
		}
		return user;
	}

	public void saveUser(final User user) {
		Connection conn = null;
		try {
			conn = this.dispatcher.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement(
							"INSERT INTO USERS(email, username, password) VALUES (?, ?, ?)",
							PreparedStatement.RETURN_GENERATED_KEYS);

			statement.setString(1, user.getEmail());
			statement.setString(2, user.getUserName());
			statement.setString(3, user.getPassword());

			statement.execute();

			final ResultSet set = statement.getGeneratedKeys();

			user.setId(set.getInt(1));

		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
}
