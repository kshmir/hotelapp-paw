package ar.edu.itba.it.paw.hotelapp.repositories.impl;

import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.repositories.api.CommentRepository;

public class InMemoryCommentRepository implements CommentRepository {
	public void saveComment(final Hotel hotel, final Comment comment) {
		hotel.getComments().add(comment);

	}
}
