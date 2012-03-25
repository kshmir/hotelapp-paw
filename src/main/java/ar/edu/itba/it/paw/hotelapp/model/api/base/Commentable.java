package ar.edu.itba.it.paw.hotelapp.model.api.base;

import java.util.Collection;

import ar.edu.itba.it.paw.collections.LazyCollection;
import ar.edu.itba.it.paw.hotelapp.model.api.Comment;

/**
 * Represents any kind of entity that might have comments
 * 
 * @author cris
 */
public interface Commentable {

	/**
	 * The ID of the commentable type, is usually the same as the Entity's ID,
	 * say, for example, a blog post's id
	 * 
	 * @return The id of the entity.
	 */
	public int getCommentableId();

	/**
	 * The type of the commentable type. Normally the class name of the
	 * implementation.
	 * 
	 * @return The class name of the implementation.
	 */
	public String getCommentableType();

	/**
	 * The list of comments attached to this instance. A good idea is to make
	 * this collection lazy using {@link LazyCollection} or a hibernate mapping.
	 * 
	 * @return A list of comments.
	 */
	public Collection<Comment> getComments();

	/**
	 * Sets the comments
	 * 
	 * @param comments
	 */
	public void setComments(Collection<Comment> comments);
}
