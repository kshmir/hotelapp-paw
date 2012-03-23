package ar.edu.itba.it.paw.hotelapp.repositories.api;

import ar.edu.itba.it.paw.hotelapp.model.api.User;

public interface UserRepository {
	public User getUser(String userName);

	public User getUser(String userName, String password);

	public void saveUser(User user);
}
