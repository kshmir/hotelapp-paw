package ar.edu.itba.it.paw.hotelapp.repositories;

import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;
import ar.edu.itba.it.paw.hotelapp.repositories.api.UserRepository;
import ar.edu.itba.it.paw.hotelapp.repositories.impl.SQLHotelRepository;
import ar.edu.itba.it.paw.hotelapp.repositories.impl.SQLUserRepository;

public class RepositoryFactory {

	private static HotelRepository hotelRepository = null;
	private static UserRepository userRepository = null;

	private static boolean repositoriesLoaded = false;

	private static void loadRepositories() {

		userRepository = new SQLUserRepository();
		hotelRepository = new SQLHotelRepository(userRepository);

		repositoriesLoaded = true;
	}

	public static HotelRepository getHotelRepository() {
		if (!repositoriesLoaded) {
			loadRepositories();
		}
		return hotelRepository;
	}

	public static UserRepository getUserRepository() {
		if (!repositoriesLoaded) {
			loadRepositories();
		}
		return userRepository;
	}
}
