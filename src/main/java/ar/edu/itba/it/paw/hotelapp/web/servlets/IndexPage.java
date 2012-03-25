package ar.edu.itba.it.paw.hotelapp.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.hotelapp.model.api.User;
import ar.edu.itba.it.paw.hotelapp.repositories.RepositoryFactory;
import ar.edu.itba.it.paw.hotelapp.repositories.api.UserRepository;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Persister;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Resolver;
import ar.edu.itba.it.paw.hotelapp.web.handlers.impl.ByParamsUserResolver;
import ar.edu.itba.it.paw.hotelapp.web.handlers.impl.SessionUserManager;
import ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper;

public class IndexPage extends HttpServlet {
	private static final long serialVersionUID = -4705972479934097038L;

	private static final String indexPage = "index/index.jsp";
	private static final String errorPage = "index/error.jsp";

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		HtmlHelper.render(indexPage, req, resp);
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final UserRepository userRepo = RepositoryFactory.getUserRepository();
		final Resolver<User> userResolver = new ByParamsUserResolver(req,
				userRepo);

		final Persister<User> userPersister = new SessionUserManager(req);

		try {
			final User u = userResolver.resolve();
			userPersister.persist(u);
			resp.sendRedirect("hotels");
		} catch (final IllegalArgumentException e) {
			HtmlHelper.render(errorPage, req, resp);
		}
	}
}
