package ar.edu.itba.it.paw.hotelapp.web.handlers.impl;

import javax.servlet.http.HttpServletRequest;

import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Resolver;

public class ByParamsHotelResolver implements Resolver<Hotel> {

	private HttpServletRequest request;
	private HotelRepository repository;

	public ByParamsHotelResolver(final HttpServletRequest request,
			final HotelRepository repo) {
		this.request = request;
		this.repository = repo;
	}

	public Hotel resolve() {
		try {
			if (this.request.getParameter("hotel_id") != null) {
				final int hotelId = Integer.valueOf(this.request
						.getParameter("hotel_id"));
				return this.repository.getHotelById(hotelId);
			}
			throw new Exception();
		} catch (final Exception e) {
			throw new IllegalArgumentException(
					"Invalid hotel_id or not supplied");
		}
	}
}
