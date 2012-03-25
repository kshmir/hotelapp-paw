package ar.edu.itba.it.paw.hotelapp.web.handlers.impl;

import javax.servlet.http.HttpServletRequest;

import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleHotel;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Builder;

/**
 * Builds a hotel implementation from the parameters sent to the servlet
 * 
 * @author cris
 */
public class ByParamsHotelBuilder implements Builder<Hotel> {

	private HttpServletRequest request;

	public ByParamsHotelBuilder(final HttpServletRequest request) {
		this.request = request;
	}

	public Hotel buildNew() {
		try {
			if (this.request.getParameter("hotel_name") != null
					&& this.request.getParameter("hotel_description") != null) {
				final String hotelName = this.request
						.getParameter("hotel_name");
				final String hotelContent = this.request
						.getParameter("hotel_description");

				return new SimpleHotel(hotelName, hotelContent);
			}
			throw new Exception();
		} catch (final Exception e) {
			throw new IllegalArgumentException("Illegal parameters given");
		}
	}
}
