package ar.edu.itba.it.paw.hotelapp.model.api.base;

/**
 * Marks something as identificable
 * 
 * @author cris
 */
public interface Identificable {
	/**
	 * @return The ID of the object
	 */
	public Integer getId();

	/**
	 * @param id
	 *            Sets the id of a newly created instance, should not be used
	 *            otherwise
	 */
	public void setId(int id);
}
