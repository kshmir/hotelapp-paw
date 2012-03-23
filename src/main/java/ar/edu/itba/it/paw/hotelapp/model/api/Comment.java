package ar.edu.itba.it.paw.hotelapp.model.api;

public interface Comment extends Identificable {
	public String getContent();

	public User getUser();
}
