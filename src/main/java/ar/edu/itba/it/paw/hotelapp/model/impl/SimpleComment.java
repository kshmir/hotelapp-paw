package ar.edu.itba.it.paw.hotelapp.model.impl;

import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.User;
import ar.edu.itba.it.paw.hotelapp.model.api.base.Commentable;

/**
 * Comment implementation
 * 
 * @author cris
 */
public class SimpleComment implements Comment {

	private User user;
	private String content;
	private Integer id;
	private boolean isDirty = false;
	private Commentable owner;

	public SimpleComment(final String content, final User user) {
		this(content, user, null);
	}

	public SimpleComment(final String content, final User user,
			final Commentable owner) {
		this.content = content;
		this.user = user;
		this.owner = owner;
	}

	public String getContent() {
		return this.content;
	}

	public User getUser() {
		return this.user;
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
		final SimpleComment other = (SimpleComment) obj;
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

	public Commentable getOwner() {
		return this.owner;
	}

	public void setOwner(final Commentable owner) {
		this.owner = owner;
	}
}
