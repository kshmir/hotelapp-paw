package ar.edu.itba.it.paw.hotelapp.repositories.impl;

import java.util.List;

import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;

/**
 * Base implementation for quick applications
 * 
 * @author cris
 */
public class InMemoryHotelRepository implements HotelRepository {

	private List<Hotel> hotels;

	public InMemoryHotelRepository(final List<Hotel> hotels) {
		this.hotels = hotels;
	}

	public List<Hotel> getHotels() {
		return this.hotels;
	}

	/**
	 * @throws Exception
	 *             when no hotel is found
	 */
	public Hotel getHotelById(final int id) throws IllegalArgumentException {
		for (final Hotel hotel : this.hotels) {
			if (hotel.getId() == id) {
				return hotel;
			}
		}
		throw new IllegalArgumentException("Hotel not found");
	}

	public void saveOrUpdateHotel(final Hotel hotel) {
		this.hotels.add(hotel);
	}

	public void deleteHotel(final Hotel hotel) {
		this.hotels.remove(hotel);
	}
}
