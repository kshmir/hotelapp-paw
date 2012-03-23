package ar.edu.itba.it.paw.hotelapp.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.model.api.User;
import ar.edu.itba.it.paw.hotelapp.repositories.RepositoryFactory;
import ar.edu.itba.it.paw.hotelapp.repositories.api.CommentRepository;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Builder;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Resolver;
import ar.edu.itba.it.paw.hotelapp.web.handlers.impl.ByParamsHotelCommentBuilder;
import ar.edu.itba.it.paw.hotelapp.web.handlers.impl.ByParamsHotelResolver;
import ar.edu.itba.it.paw.hotelapp.web.handlers.impl.SessionUserManager;

public class CommentsPage extends HttpServlet {

	private static final long serialVersionUID = -2952133070629644135L;

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final Resolver<Hotel> hotelResolver = new ByParamsHotelResolver(req,
				RepositoryFactory.getHotelRepository());
		final Resolver<User> userResolver = new SessionUserManager(req);
		final Builder<Comment> commentResolver = new ByParamsHotelCommentBuilder(
				req, userResolver);

		final CommentRepository commentRepo = RepositoryFactory
				.getCommentRepository();

		final Hotel hotel = hotelResolver.resolve();
		final Comment comment = commentResolver.buildNew();

		commentRepo.saveComment(hotel, comment);

		resp.sendRedirect(req.getHeader("Referer"));
	}
}
