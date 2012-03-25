package ar.edu.itba.it.paw.hotelapp.web.handlers.api;

/**
 * Allows to build a new memory instance from certain given parameters. Differs
 * from {@link Resolver}, which gets
 * 
 * @author cris
 */
public interface Builder<T> {
	/**
	 * Builds a new instance of T based on the given parameters
	 * 
	 * @return The instance of T built
	 */
	public T buildNew();
}
