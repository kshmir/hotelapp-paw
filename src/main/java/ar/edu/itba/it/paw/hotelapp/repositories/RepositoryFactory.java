package ar.edu.itba.it.paw.hotelapp.repositories;

import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.model.api.User;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleComment;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleHotel;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleUser;
import ar.edu.itba.it.paw.hotelapp.repositories.api.CommentRepository;
import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;
import ar.edu.itba.it.paw.hotelapp.repositories.api.UserRepository;
import ar.edu.itba.it.paw.hotelapp.repositories.impl.InMemoryCommentRepository;
import ar.edu.itba.it.paw.hotelapp.repositories.impl.InMemoryHotelRepository;
import ar.edu.itba.it.paw.hotelapp.repositories.impl.InMemoryUserRepository;

public class RepositoryFactory {

	private static HotelRepository hotelRepository = null;
	private static UserRepository userRepository = null;
	private static CommentRepository commentRepository = null;

	private static boolean repositoriesLoaded = false;

	private static void loadRepositories() {

		final List<User> users = new ArrayList<User>();
		final List<Hotel> hotels = new ArrayList<Hotel>();

		final List<Comment> comments = new ArrayList<Comment>();

		final User user = new SimpleUser("cristian", "entrada",
				"criis.pereyra@gmail.com");

		comments.add(new SimpleComment("uno de los mejores!", user));

		hotels.add(new SimpleHotel(1, "Sheraton", "Caro", comments));
		hotels.add(new SimpleHotel(2, "Sheratong", "Barato"));

		users.add(user);

		hotelRepository = new InMemoryHotelRepository(hotels);
		userRepository = new InMemoryUserRepository(users);
		commentRepository = new InMemoryCommentRepository();

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

	public static CommentRepository getCommentRepository() {
		if (!repositoriesLoaded) {
			loadRepositories();
		}
		return commentRepository;
	}
}
