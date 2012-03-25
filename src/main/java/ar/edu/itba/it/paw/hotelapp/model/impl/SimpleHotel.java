package ar.edu.itba.it.paw.hotelapp.model.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;

/**
 * Hotel implementation
 * 
 * @author cris
 */
public class SimpleHotel implements Hotel {

	private Integer code;
	private String name;
	private String description;
	private Collection<Comment> comments;
	private boolean isDirty = false;

	public SimpleHotel(final String name, final String description) {
		this(name, description, new ArrayList<Comment>());
	}

	public SimpleHotel(final String name, final String description,
			final List<Comment> comments) {
		this.name = name;
		this.description = description;
		this.comments = comments;
	}

	public Integer getId() {
		return this.code;
	}

	public void setId(final int code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + this.code;
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
		final SimpleHotel other = (SimpleHotel) obj;
		if (this.code != other.code) {
			return false;
		}
		return true;
	}

	public int getCommentableId() {
		return this.code;
	}

	public String getCommentableType() {
		return this.getClass().getSimpleName();
	}

	public boolean isDirty() {
		return this.isDirty;
	}

	public void declareDirty() {
		this.isDirty = true;
	}
}
