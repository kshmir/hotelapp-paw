package ar.edu.itba.it.paw.hotelapp.web.servlets;

import static ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper.bodyTagClose;
import static ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper.bodyTagOpen;
import static ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper.htmlTagClose;
import static ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper.htmlTagOpen;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.repositories.RepositoryFactory;
import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;
import ar.edu.itba.it.paw.hotelapp.web.util.PathResolver;

public class HotelPage extends HttpServlet {

	private static final long serialVersionUID = -409882906155515870L;

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final HotelRepository hotelRepository = RepositoryFactory
				.getHotelRepository();

		try {
			final Hotel hotel = hotelRepository.getHotelById(PathResolver
					.getResourceIdFromPath(req.getPathInfo()));

			final PrintWriter writer = resp.getWriter();

			htmlTagOpen(writer);
			bodyTagOpen(writer);

			writer.write("<h1>");

			writer.write(hotel.getName());

			writer.write("</h1>");

			writer.write("<p>");

			writer.write(hotel.getDescription());

			writer.write("</p>");

			writer.write("<p> Codigo: ");

			writer.write(String.valueOf(hotel.getId()));

			writer.write("<hr/>");

			writer.write("<h3>Comentarios</h3>");

			writer.write("<ul>");
			for (final Comment comment : hotel.getComments()) {
				writer.write("<li>");
				writer.write("Por: " + comment.getUser().getUserName() + " ("
						+ comment.getUser().getEmail() + ")");
				writer.write("<br/>");
				writer.write("<b>" + comment.getContent() + "</b>");
				writer.write("</li>");
			}
			writer.write("</ul>");

			writer.write("<form method='POST' action='"
					+ req.getContextPath()
					+ "/comments' ><input type='hidden' name='hotel_id' value='"
					+ hotel.getId()
					+ "'><input type='text' name='comment_content'><input type='submit'/></form>");

			writer.write("<hr/>");

			writer.write("</p>");

			bodyTagClose(writer);
			htmlTagClose(writer);

		} catch (final Exception e) {
			resp.setStatus(404);
			resp.sendRedirect("../error");
		}
	}
}
