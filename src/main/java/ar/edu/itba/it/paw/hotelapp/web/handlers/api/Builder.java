package ar.edu.itba.it.paw.hotelapp.web.handlers.api;

public interface Builder<T> {
	/**
	 * Builds a new instance of T based on the given parameters
	 * 
	 * @return The instance of T built
	 */
	public T buildNew();
}
