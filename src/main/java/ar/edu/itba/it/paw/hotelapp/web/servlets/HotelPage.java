package ar.edu.itba.it.paw.hotelapp.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.repositories.RepositoryFactory;
import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Updater;
import ar.edu.itba.it.paw.hotelapp.web.handlers.impl.ByParamsHotelUpdater;
import ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper;
import ar.edu.itba.it.paw.hotelapp.web.util.PathResolver;

public class HotelPage extends HttpServlet {

	private static final long serialVersionUID = -409882906155515870L;

	private static final String showPage = "hotels/show.jsp";

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final HotelRepository hotelRepository = RepositoryFactory
				.getHotelRepository();

		try {
			final Hotel hotel = hotelRepository.getHotelById(PathResolver
					.getResourceIdFromPath(req.getPathInfo()));

			req.setAttribute("hotel", hotel);

			HtmlHelper.render(showPage, req, resp);

		} catch (final Exception e) {
			resp.setStatus(404);
			resp.sendRedirect("../error");
		}
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final HotelRepository hotelRepository = RepositoryFactory
				.getHotelRepository();

		final Updater<Hotel> updater = new ByParamsHotelUpdater(req);

		try {
			Hotel hotel = hotelRepository.getHotelById(PathResolver
					.getResourceIdFromPath(req.getPathInfo()));

			hotel = updater.update(hotel);

			hotelRepository.saveOrUpdateHotel(hotel);

			resp.sendRedirect(req.getHeader("Referer"));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doDelete(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final HotelRepository hotelRepository = RepositoryFactory
				.getHotelRepository();

		try {
			final Hotel hotel = hotelRepository.getHotelById(PathResolver
					.getResourceIdFromPath(req.getPathInfo()));

			hotelRepository.deleteHotel(hotel);

		} catch (final Exception e) {
			e.printStackTrace();
		}

	}
}
