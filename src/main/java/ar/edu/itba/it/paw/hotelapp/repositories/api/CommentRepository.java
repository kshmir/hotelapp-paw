package ar.edu.itba.it.paw.hotelapp.repositories.api;

import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;

public interface CommentRepository {
	public void saveComment(Hotel hotel, Comment comment);
}
