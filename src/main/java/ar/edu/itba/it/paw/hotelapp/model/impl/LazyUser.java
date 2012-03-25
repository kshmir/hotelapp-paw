package ar.edu.itba.it.paw.hotelapp.model.impl;

import org.apache.commons.collections.Factory;

import ar.edu.itba.it.paw.hotelapp.model.api.User;

/**
 * Lazy pattern implementation of a User, allows comments to have users loaded
 * on demand.
 * 
 * @author cris
 */
public class LazyUser implements User {

	private User user;
	private Factory factory;

	public LazyUser(final Factory factory) {
		this.factory = factory;
	}

	private User getUser() {
		if (this.user == null) {
			this.user = (User) this.factory.create();
		}

		return this.user;
	}

	public Integer getId() {
		return this.getUser().getId();
	}

	public void setId(final int id) {
		this.getUser().setId(id);
	}

	public String getUserName() {
		return this.getUser().getUserName();
	}

	public String getEmail() {
		return this.getUser().getEmail();
	}

	public String getPassword() {
		return this.getUser().getPassword();
	}

	public boolean isDirty() {
		return this.getUser().isDirty();
	}

	public void declareDirty() {
		this.getUser().isDirty();
	}

}