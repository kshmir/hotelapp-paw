package ar.edu.itba.it.paw.hotelapp.model.impl;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;

public class SimpleHotel implements Hotel {

	private int code;
	private String name;
	private String description;
	private List<Comment> comments;

	public SimpleHotel(final int code, final String name,
			final String description) {
		this(code, name, description, new ArrayList<Comment>());
	}

	public SimpleHotel(final int code, final String name,
			final String description, final List<Comment> comments) {
		this.code = code;
		this.name = name;
		this.description = description;
		this.comments = comments;
	}

	public int getId() {
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

	public List<Comment> getComments() {
		return this.comments;
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

}
