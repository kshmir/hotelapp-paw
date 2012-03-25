package ar.edu.itba.it.paw.hotelapp.repositories.base;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.model.api.User;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleComment;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleHotel;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleUser;
import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;
import ar.edu.itba.it.paw.hotelapp.repositories.api.UserRepository;
import ar.edu.itba.it.paw.hotelapp.test.TransactionalTest;

public abstract class HotelRepositoryTest extends TransactionalTest {

	private HotelRepository repository;
	private UserRepository userRepository;

	public HotelRepositoryTest() {
		this.repository = this.getRepository();
		this.userRepository = this.getUserRepository();
	}

	public abstract HotelRepository getRepository();

	public abstract UserRepository getUserRepository();

	private void setup() {
		User user = new SimpleUser("cristian", "password", "email");

		this.userRepository.saveUser(user);

		user = this.userRepository.getUser(user.getUserName());

		final Hotel hotel = new SimpleHotel("Sheraton", "lalala");

		this.repository.saveOrUpdateHotel(hotel);

		final Comment comment = new SimpleComment("hola", user, hotel);

		hotel.getComments().add(comment);

		this.repository.saveOrUpdateHotel(hotel);
	}

	@Test
	public void getHotelsTest() {
		this.setup();
		final List<Hotel> hotels = this.repository.getHotels();

		Assert.assertTrue(hotels.size() == 1);
		Assert.assertNotNull(hotels);
		Assert.assertEquals(this.repository.getHotels(), hotels);
	}

	@Test
	public void getHotelByIdTest() {
		this.setup();
		Hotel hotel = this.repository.getHotelById(0);

		Assert.assertNull(hotel);

		hotel = this.repository.getHotelById(1);

		Assert.assertNotNull(hotel);
		Assert.assertNotNull(hotel.getName());
		Assert.assertNotNull(hotel.getDescription());
		Assert.assertNotNull(hotel.getComments());
		Assert.assertTrue(hotel.getComments().size() > 0);

		for (final Comment comment : hotel.getComments()) {
			Assert.assertEquals("hola", comment.getContent());
		}
	}

	@Test
	public void deleteHotelTest() {
		this.setup();

		final Hotel hotel = this.repository.getHotelById(1);

		Assert.assertNotNull(hotel);

		this.repository.deleteHotel(hotel);

		Assert.assertNull(this.repository.getHotelById(1));

	}
}
