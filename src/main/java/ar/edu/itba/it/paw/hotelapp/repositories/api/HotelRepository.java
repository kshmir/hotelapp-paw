package ar.edu.itba.it.paw.hotelapp.repositories.api;

import java.util.List;

import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;

/**
 * Hotel Repository, allows managing hotels and its comments
 * 
 * @author cris
 */
public interface HotelRepository {
	/**
	 * @return A list of all the hotels
	 */
	public List<Hotel> getHotels();

	/**
	 * @param id
	 *            The id of the hotel to return
	 * @return A hotel
	 */
	public Hotel getHotelById(int id);

	/**
	 * @param hotel
	 *            The hotel to persist
	 */
	public void saveOrUpdateHotel(Hotel hotel);

	/**
	 * @param hotel
	 *            The hotel to delete
	 */
	public void deleteHotel(Hotel hotel);
}
