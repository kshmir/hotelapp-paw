package ar.edu.itba.it.paw.hotelapp.repositories.base;

import org.junit.Assert;
import org.junit.Test;

import ar.edu.itba.it.paw.hotelapp.model.api.User;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleUser;
import ar.edu.itba.it.paw.hotelapp.repositories.api.UserRepository;
import ar.edu.itba.it.paw.hotelapp.test.TransactionalTest;

public abstract class UserRepositoryTest extends TransactionalTest {

	private UserRepository repository;

	public abstract UserRepository getRepository();

	public UserRepositoryTest() {
		this.repository = this.getRepository();
	}

	private void setup() {
		final User user = new SimpleUser("cris", "holahola",
				"criis.pereyra@gmail.com");

		this.repository.saveUser(user);
	}

	@Test
	public void getUserByName() {
		this.setup();

		User user = this.repository.getUser("cris");

		Assert.assertNotNull(user);
		Assert.assertNotNull(user.getUserName());
		Assert.assertNotNull(user.getEmail());
		Assert.assertNotNull(user.getPassword());
		Assert.assertEquals("cris", user.getUserName());

		user = this.repository.getUser("cris2");

		Assert.assertNull(user);
	}

	@Test
	public void getUserByNameAndPassword() {

		this.setup();

		final User user = this.repository.getUser("cris");

		Assert.assertNotNull(user);

		final User user2 = this.repository.getUser(user.getUserName(),
				user.getPassword());

		Assert.assertNotNull(user2);
		Assert.assertEquals(user, user2);
		Assert.assertEquals(user2, user);
	}
}
