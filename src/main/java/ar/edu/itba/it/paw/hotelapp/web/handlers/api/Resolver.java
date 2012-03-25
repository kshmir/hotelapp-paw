package ar.edu.itba.it.paw.hotelapp.web.handlers.api;

/**
 * Take
 * 
 * @author cris
 */
public interface Resolver<T> {
	/**
	 * Resolves an existing instance of T from the given parameters
	 * 
	 * @return The instance of T retrieved
	 */
	public T resolve();
}
