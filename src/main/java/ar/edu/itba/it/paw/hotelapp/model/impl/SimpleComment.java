package ar.edu.itba.it.paw.hotelapp.model.impl;

import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.User;

public class SimpleComment implements Comment {

	private User user;
	private String content;
	private int id;

	public SimpleComment(final String content, final User user) {
		this.content = content;
		this.user = user;
	}

	public String getContent() {
		return this.content;
	}

	public User getUser() {
		return this.user;
	}

	public int getId() {
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
		final SimpleComment other = (SimpleComment) obj;
		if (this.id != other.id) {
			return false;
		}
		return true;
	}
}
