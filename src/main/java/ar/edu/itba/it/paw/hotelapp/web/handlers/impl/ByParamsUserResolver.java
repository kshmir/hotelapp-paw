package ar.edu.itba.it.paw.hotelapp.web.handlers.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.NotImplementedException;

import ar.edu.itba.it.paw.hotelapp.model.api.User;
import ar.edu.itba.it.paw.hotelapp.repositories.api.UserRepository;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Resolver;

public class ByParamsUserResolver implements Resolver<User> {

	private HttpServletRequest request;
	private UserRepository repository;

	public ByParamsUserResolver(final HttpServletRequest request,
			final UserRepository repository) {
		this.request = request;
		this.repository = repository;
	}

	public User resolveNew() {
		throw new NotImplementedException();
	}

	public User resolve() {
		try {
			final User toReturn;
			if ((toReturn = this.repository.getUser(
					this.request.getParameter("user_name"),
					this.request.getParameter("user_password"))) != null) {
				return toReturn;
			}
			throw new Exception();
		} catch (final Exception e) {
			throw new IllegalArgumentException("Invalid username/password");
		}
	}
}
