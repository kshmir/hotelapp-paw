package ar.edu.itba.it.paw.hotelapp.repositories.api;

import ar.edu.itba.it.paw.hotelapp.model.api.User;

/**
 * Repository for users
 * 
 * @author cris
 */
public interface UserRepository {
	/**
	 * @param id
	 *            If of the user to get
	 */
	public User getUserById(final int id);

	/**
	 * @param userName
	 *            Name of the user to get
	 */
	public User getUser(String userName);

	/**
	 * @param userName
	 *            Name of the user to get
	 * @param password
	 *            Name of the password to
	 * @return null if not found, or the user matching the username/password
	 */
	public User getUser(String userName, String password);

	/**
	 * Saves a new user, not really used yet
	 */
	public void saveUser(User user);
}
