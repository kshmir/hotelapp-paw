package ar.edu.itba.it.paw.hotelapp.model.api;

public interface Hotel extends Commentable, Identificable {
	public String getName();

	public void setName(String name);

	public String getDescription();

	public void setDescription(String description);
}
