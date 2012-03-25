package ar.edu.itba.it.paw.hotelapp.model.api;

import ar.edu.itba.it.paw.hotelapp.model.api.base.Entity;
import ar.edu.itba.it.paw.hotelapp.model.api.base.Identificable;

/**
 * User interface
 * 
 * @author cris
 */
public interface User extends Identificable, Entity {
	public String getUserName();

	public String getEmail();

	public String getPassword();
}
