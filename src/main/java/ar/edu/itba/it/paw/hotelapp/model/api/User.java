package ar.edu.itba.it.paw.hotelapp.model.api;

public interface User extends Identificable {
	public String getUserName();

	public String getEmail();

	public String getPassword();
}
