package ar.edu.itba.it.paw.hotelapp.repositories.impl;

import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;

public class SQLHotelRepositoryTest extends HotelRepositoryTest {

	private HotelRepository repository;

	@Override
	public HotelRepository getRepository() {
		if (this.repository == null) {
			this.repository = new SQLHotelRepository(this.getDispatcher());
		}
		return this.repository;
	}
}
