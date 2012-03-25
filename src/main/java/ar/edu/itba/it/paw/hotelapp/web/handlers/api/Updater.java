package ar.edu.itba.it.paw.hotelapp.web.handlers.api;

/**
 * Updates a given object instance to another one, and sets it as dirty if it is
 * modified
 * 
 * @author cris
 */
public interface Updater<T> {

	/**
	 * 
	 * @param original
	 *            The original T
	 * @return The original T, or another, but set as dirty if changes were made
	 */
	T update(T original);
}
