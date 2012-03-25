package ar.edu.itba.it.paw.hotelapp.model.api;

import ar.edu.itba.it.paw.hotelapp.model.api.base.Commentable;
import ar.edu.itba.it.paw.hotelapp.model.api.base.Entity;
import ar.edu.itba.it.paw.hotelapp.model.api.base.Identificable;

/**
 * Hotel interface
 * 
 * @author cris
 */
public interface Hotel extends Commentable, Identificable, Entity {
	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);
}
