package ar.edu.itba.it.paw.hotelapp.repositories.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleHotel;
import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;

public abstract class HotelRepositoryTest extends TransactionalTest {

	private HotelRepository repository;

	public HotelRepositoryTest() {
		this.repository = this.getRepository();
	}

	public abstract HotelRepository getRepository();

	private void setup() {
		final Hotel hotel = new SimpleHotel(0, "Sheraton", "lalala");

		this.repository.saveHotel(hotel);
	}

	@Test
	public void getHotels() {
		this.setup();
		final List<Hotel> hotels = this.repository.getHotels();

		Assert.assertTrue(hotels.size() == 1);
		Assert.assertNotNull(hotels);
		Assert.assertEquals(this.repository.getHotels(), hotels);
	}

	@Test
	public void getHotelById() {
		this.setup();
		Hotel hotel = this.repository.getHotelById(0);

		Assert.assertNull(hotel);

		hotel = this.repository.getHotelById(1);

		Assert.assertNotNull(hotel);
		Assert.assertNotNull(hotel.getName());
		Assert.assertNotNull(hotel.getDescription());
		Assert.assertNotNull(hotel.getComments());
	}

}
