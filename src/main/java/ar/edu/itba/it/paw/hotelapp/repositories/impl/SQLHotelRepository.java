package ar.edu.itba.it.paw.hotelapp.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.db.ConnectionDispatcher;
import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleHotel;
import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;

public class SQLHotelRepository implements HotelRepository {

	private ConnectionDispatcher dispatcher;

	public SQLHotelRepository() {
		this(null);
	}

	public SQLHotelRepository(final ConnectionDispatcher dispatcher) {
		if (dispatcher == null) {
			this.dispatcher = ConnectionDispatcher.getDispatcher();
		} else {
			this.dispatcher = dispatcher;
		}
	}

	public List<Hotel> getHotels() {
		final List<Hotel> hotels = new ArrayList<Hotel>();
		Connection conn = null;
		try {
			conn = this.dispatcher.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM hotels");

			statement.execute();
			final ResultSet set = statement.getResultSet();

			while (set.next() && !set.isAfterLast()) {
				final int id = set.getInt("id");
				final String name = set.getString("name");
				final String description = set.getString("description");

				final Hotel hotel = new SimpleHotel(id, name, description);

				hotels.add(hotel);
			}
		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
		}
		return hotels;
	}

	public Hotel getHotelById(final int id) {
		Hotel hotel = null;
		Connection conn = null;
		try {
			conn = this.dispatcher.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM hotels WHERE id = ?");

			statement.setInt(1, id);

			statement.execute();
			final ResultSet set = statement.getResultSet();

			while (set.next() && !set.isAfterLast()) {
				final int hotelId = set.getInt("id");
				final String name = set.getString("name");
				final String description = set.getString("description");

				hotel = new SimpleHotel(hotelId, name, description);
			}

		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
		}

		return hotel;
	}

	public void saveHotel(final Hotel hotel) {
		Connection conn = null;
		try {
			conn = this.dispatcher.getConnection();
			final PreparedStatement statement = conn
					.prepareStatement("INSERT INTO HOTELS VALUES (NULL, ?, ?)");

			statement.setString(1, hotel.getDescription());
			statement.setString(2, hotel.getName());

			statement.execute();

		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

}
