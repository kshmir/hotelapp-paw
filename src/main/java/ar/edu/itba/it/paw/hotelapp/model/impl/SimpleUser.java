package ar.edu.itba.it.paw.hotelapp.model.impl;

import ar.edu.itba.it.paw.hotelapp.model.api.User;

/**
 * Base user implementation
 * 
 * @author cris
 */
public class SimpleUser implements User {

	private String userName;
	private String password;
	private String email;
	private Integer id;
	private boolean isDirty;

	public SimpleUser(final String userName, final String password,
			final String email) {
		this.userName = userName;
		this.password = password;
		this.email = email;
	}

	public String getUserName() {
		return this.userName;
	}

	public String getPassword() {
		return this.password;
	}

	public String getEmail() {
		return this.email;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.id;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final SimpleUser other = (SimpleUser) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}

	public boolean isDirty() {
		return this.isDirty;
	}

	public void declareDirty() {
		this.isDirty = true;
	}
}
