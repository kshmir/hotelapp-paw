package ar.edu.itba.it.paw.hotelapp.model.api.base;

/**
 * Base interface of an entity, contains only one state that I could think of:
 * whether the entity is dirty or not, dirty means having something changed and
 * not persisted. It also has declareDirty to set he entity as dirty.
 * 
 * @author cris
 * 
 */
public interface Entity extends Identificable {
	/**
	 * @return Whether the entity has modifications or not
	 */
	public boolean isDirty();

	/**
	 * Marks the entity as modified
	 */
	public void declareDirty();
}
