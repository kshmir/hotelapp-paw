package ar.edu.itba.it.paw.hotelapp.model.api;

import ar.edu.itba.it.paw.hotelapp.model.api.base.Commentable;
import ar.edu.itba.it.paw.hotelapp.model.api.base.Entity;
import ar.edu.itba.it.paw.hotelapp.model.api.base.Identificable;

/**
 * Represents a comment on any Commentable entity
 * 
 * @author cris
 */
public interface Comment extends Identificable, Entity {

	/**
	 * @return Contents of the comment.
	 */
	public String getContent();

	/**
	 * @return User which is author of the comment
	 */
	public User getUser();

	/**
	 * @return Owner of the commentable
	 */
	public Commentable getOwner();

	/**
	 * @param owner
	 *            Prepares the commentable
	 */
	public void setOwner(Commentable owner);
}
