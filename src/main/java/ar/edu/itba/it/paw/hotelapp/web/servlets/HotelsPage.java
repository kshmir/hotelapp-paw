package ar.edu.itba.it.paw.hotelapp.web.servlets;

import static ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper.bodyTagClose;
import static ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper.bodyTagOpen;
import static ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper.htmlTagClose;
import static ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper.htmlTagOpen;
import static ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper.linkTo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.repositories.RepositoryFactory;
import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;

public class HotelsPage extends HttpServlet {

	private static final long serialVersionUID = 4345392049436856928L;

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final HotelRepository hotelRepository = RepositoryFactory
				.getHotelRepository();

		final PrintWriter writer = resp.getWriter();

		htmlTagOpen(writer);
		bodyTagOpen(writer);

		writer.write("<ul>");
		for (final Hotel hotel : hotelRepository.getHotels()) {
			writer.write("<li>"
					+ linkTo("hotels/" + hotel.getId(), hotel.getName())
					+ "</li>");
		}
		writer.write("</ul><a href='./?logout=true'>BYe bye</a>");

		bodyTagClose(writer);
		htmlTagClose(writer);
	}
}
