package ar.edu.itba.it.paw.hotelapp.web.handlers.impl;

import javax.servlet.http.HttpServletRequest;

import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.web.handlers.api.Updater;

/**
 * Updates a hotel from it's parameters
 * 
 * @author cris
 */
public class ByParamsHotelUpdater implements Updater<Hotel> {

	private HttpServletRequest request;

	public ByParamsHotelUpdater(final HttpServletRequest request) {
		this.request = request;
	}

	public Hotel update(final Hotel original) {
		if (this.request.getParameter("hotel_name") != null) {
			original.setName(this.request.getParameter("hotel_name"));
			original.declareDirty();
		}

		if (this.request.getParameter("hotel_description") != null) {
			original.setDescription(this.request
					.getParameter("hotel_description"));

			original.declareDirty();
		}
		return original;
	}

}
