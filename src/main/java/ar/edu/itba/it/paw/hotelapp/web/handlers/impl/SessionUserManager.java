package ar.edu.itba.it.paw.hotelapp.web.handlers.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.edu.itba.it.paw.hotelapp.model.api.User;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Manager;

/**
 * Manages all the persistance of a user inside the session
 * 
 * @author cris
 */
public class SessionUserManager extends Manager<User> {

	private HttpSession session;
	private HttpServletRequest request;

	public SessionUserManager(final HttpServletRequest request) {
		this.session = request.getSession();
		this.request = request;
	}

	@Override
	public User resolve() {
		return (User) this.session.getAttribute("user");
	}

	@Override
	public boolean unlink() {
		if (this.request.getParameter("logout") != null
				&& this.request.getParameter("logout").equals("true")) {
			this.session.setAttribute("user", null);
			return true;
		}
		return false;
	}

	@Override
	public boolean persist(final User t) {
		this.session.setAttribute("user", t);
		return true;
	}

}
