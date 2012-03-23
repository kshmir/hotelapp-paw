package ar.edu.itba.it.paw.hotelapp.repositories.api;

import java.util.List;

import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;

public interface HotelRepository {
	public List<Hotel> getHotels();

	public Hotel getHotelById(int id);

	public void saveHotel(Hotel hotel);
}
