package ar.edu.itba.it.paw.hotelapp.repositories.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.itba.it.paw.hotelapp.model.api.User;
import ar.edu.itba.it.paw.hotelapp.repositories.api.UserRepository;

public class InMemoryUserRepository implements UserRepository {
	private Map<String, User> userMap;

	public InMemoryUserRepository(final List<User> users) {
		this.userMap = new HashMap<String, User>();
		for (final User u : users) {
			this.userMap.put(u.getUserName(), u);
		}
	}

	public User getUser(final String userName) {
		if (!this.userMap.containsKey(userName)) {
			throw new IllegalArgumentException("Invalid username passed: "
					+ userName);
		}
		return this.userMap.get(userName);
	}

	public User getUser(final String userName, final String password) {
		if (!this.userMap.containsKey(userName)) {
			throw new IllegalArgumentException("Invalid username passed: "
					+ userName);
		}
		final User user = this.userMap.get(userName);
		if (user.getPassword().equals(password)) {
			return user;
		} else {
			throw new IllegalArgumentException("Invalid password passed: "
					+ userName);
		}
	}

	public void saveUser(final User user) {
		this.userMap.put(user.getUserName(), user);
	}
}
