package ar.edu.itba.it.paw.hotelapp.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.repositories.RepositoryFactory;
import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Builder;
import ar.edu.itba.it.paw.hotelapp.web.handlers.impl.ByParamsHotelBuilder;
import ar.edu.itba.it.paw.hotelapp.web.util.HtmlHelper;

public class HotelsPage extends HttpServlet {

	private static final long serialVersionUID = 4345392049436856928L;

	private static final String indexPage = "hotels/index.jsp";

	@Override
	protected void doGet(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {
		final HotelRepository hotelRepository = RepositoryFactory
				.getHotelRepository();

		final List<Hotel> hotels = hotelRepository.getHotels();

		req.setAttribute("hotels", hotels);
		HtmlHelper.render(indexPage, req, resp);
	}

	@Override
	protected void doPost(final HttpServletRequest req,
			final HttpServletResponse resp) throws ServletException,
			IOException {

		final HotelRepository hotelRepository = RepositoryFactory
				.getHotelRepository();

		final Builder<Hotel> resolver = new ByParamsHotelBuilder(req);

		final Hotel hotel = resolver.buildNew();

		hotelRepository.saveOrUpdateHotel(hotel);

		resp.sendRedirect(req.getHeader("Referer"));
	}

}
