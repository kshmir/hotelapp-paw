package ar.edu.itba.it.paw.hotelapp.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorPage extends HttpServlet {

	private static final long serialVersionUID = 5262399522134168372L;

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		resp.getWriter().write(
				"There was an error during your request => FAIL!");
	}

}
