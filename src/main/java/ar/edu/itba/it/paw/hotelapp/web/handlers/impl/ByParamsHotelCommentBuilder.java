package ar.edu.itba.it.paw.hotelapp.web.handlers.impl;

import javax.servlet.http.HttpServletRequest;

import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.User;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleComment;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Builder;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Resolver;

public class ByParamsHotelCommentBuilder implements Builder<Comment> {

	private HttpServletRequest request;
	private Resolver<User> userResolver;

	public ByParamsHotelCommentBuilder(final HttpServletRequest request,
			final Resolver<User> userResolver) {
		this.request = request;
		this.userResolver = userResolver;
	}

	public Comment buildNew() {
		try {
			if (this.request.getParameter("comment_content") != null) {
				final User user = this.userResolver.resolve();
				final Comment c = new SimpleComment(
						this.request.getParameter("comment_content"), user);
				return c;
			}
			throw new Exception();
		} catch (final Exception e) {
			throw new IllegalArgumentException(
					"Invalid params given or no comment_content given, or invalid user session");
		}
	}
}
