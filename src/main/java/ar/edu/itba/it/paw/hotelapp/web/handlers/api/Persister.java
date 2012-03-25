package ar.edu.itba.it.paw.hotelapp.web.handlers.api;

/**
 * This interface should be used to persist in memory any kind of object (ex:
 * session)
 * 
 * @author Cristian Pereyra
 * 
 * @param <T>
 *            The kind of object to persist from
 */
public interface Persister<T> {
	/**
	 * @param t
	 *            The object to persist
	 * @return whether the object was persisted or not
	 */
	public boolean persist(T t);

	/**
	 * @return True if the object was taken of the persistance store
	 */
	public boolean unlink();
}
