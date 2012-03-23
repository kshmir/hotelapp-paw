package ar.edu.itba.it.paw.hotelapp.web.handlers.api;

import org.apache.commons.lang.NotImplementedException;

/**
 * General porpuse stub class Allows all the kind of interactions a "T"Manager
 * should have
 * 
 * @author Cristian Pereyra
 * 
 * @param <T>
 *            Should be any kind of user that we might like to use.
 */
public abstract class Manager<T> implements Builder<T>, Persister<T>,
		Resolver<T> {

	public T buildNew() {
		throw new NotImplementedException();
	}

	public boolean persist(final T t) {
		throw new NotImplementedException();
	}

	public T resolve() {
		throw new NotImplementedException();
	}

	public boolean unlink() {
		throw new NotImplementedException();
	}
}
