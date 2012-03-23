package ar.edu.itba.it.paw.hotelapp.repositories.impl;

import ar.edu.itba.it.paw.hotelapp.repositories.api.UserRepository;

public class SQLUserRepositoryTest extends UserRepositoryTest {

	private UserRepository repository;

	@Override
	public UserRepository getRepository() {
		if (this.repository == null) {
			this.repository = new SQLUserRepository(this.getDispatcher());
		}
		return this.repository;
	}

}
