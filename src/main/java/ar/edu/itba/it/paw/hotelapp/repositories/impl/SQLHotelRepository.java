package ar.edu.itba.it.paw.hotelapp.repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.Factory;

import ar.edu.itba.it.paw.collections.DeletionMemoryCollection;
import ar.edu.itba.it.paw.collections.LazyCollection;
import ar.edu.itba.it.paw.db.ConnectionDispatcher;
import ar.edu.itba.it.paw.hotelapp.model.api.Comment;
import ar.edu.itba.it.paw.hotelapp.model.api.Hotel;
import ar.edu.itba.it.paw.hotelapp.model.api.base.Commentable;
import ar.edu.itba.it.paw.hotelapp.model.impl.LazyUser;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleComment;
import ar.edu.itba.it.paw.hotelapp.model.impl.SimpleHotel;
import ar.edu.itba.it.paw.hotelapp.repositories.api.HotelRepository;
import ar.edu.itba.it.paw.hotelapp.repositories.api.UserRepository;

/**
 * Hotel Repository implementation
 * 
 * @author cris
 */
public class SQLHotelRepository implements HotelRepository {

	private ConnectionDispatcher dispatcher;

	private UserRepository userRepo;

	/**
	 * Generates a user from it's repository.
	 * 
	 * @author cris
	 */
	private class UserFactory implements Factory {
		private int userId;

		public UserFactory(final int userId) {
			this.userId = userId;
		}

		public Object create() {
			return SQLHotelRepository.this.userRepo.getUserById(this.userId);
		}
	}

	/**
	 * Generates the comments of a given commentable. This should be extracted
	 * later
	 * 
	 * @author cris
	 */
	private class CommentFactory implements Factory {

		private ConnectionDispatcher dispatcher;
		private Commentable commentable;

		public CommentFactory(final ConnectionDispatcher dispatcher,
				final Commentable commentable) {
			this.dispatcher = dispatcher;
			this.commentable = commentable;
		}

		public Object create() {
			final List<Comment> comments = new ArrayList<Comment>();
			try {
				final Connection conn = this.dispatcher.getConnection();

				final PreparedStatement statement = conn
						.prepareStatement("SELECT * FROM comments WHERE commentable_id = ? AND commentable_type = ?");

				statement.setInt(1, this.commentable.getCommentableId());
				statement.setString(2, this.commentable.getCommentableType());

				statement.execute();

				final ResultSet set = statement.getResultSet();

				while (set.next() && !set.isAfterLast()) {
					final int id = set.getInt("id");
					final int userId = set.getInt("user_id");
					final String content = set.getString("content");

					final Comment comment = new SimpleComment(content,
							new LazyUser(new UserFactory(userId)),
							this.commentable);

					comment.setId(id);
					comments.add(comment);
				}

			} catch (final Exception e) {
				e.printStackTrace();
			}
			return comments;
		}
	}

	public SQLHotelRepository() {
		this(null, null);
	}

	public SQLHotelRepository(final UserRepository usersRepo) {
		this(null, usersRepo);
	}

	public SQLHotelRepository(final ConnectionDispatcher dispatcher,
			final UserRepository usersRepo) {

		if (dispatcher == null) {
			this.dispatcher = ConnectionDispatcher.getDispatcher();
		} else {
			this.dispatcher = dispatcher;
		}

		this.userRepo = usersRepo;
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

				final Hotel hotel = new SimpleHotel(name, description);

				hotel.setId(id);

				final Collection<Comment> comments = this
						.makeHotelComments(hotel);

				hotel.setComments(comments);

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

				hotel = new SimpleHotel(name, description);

				hotel.setId(hotelId);

				final Collection<Comment> comments = this
						.makeHotelComments(hotel);

				hotel.setComments(comments);
			}

		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
		}

		return hotel;
	}

	private void saveOrUpdateComment(final Comment c) throws Exception {

		final Connection conn = this.dispatcher.getConnection();
		PreparedStatement statement = null;

		if (c.getId() == null) {
			statement = conn
					.prepareStatement("INSERT INTO COMMENTS(user_id, commentable_id, commentable_type, content) VALUES (?, ?, ?, ?)");
		} else if (c.isDirty()) {
			statement = conn
					.prepareStatement("UPDATE COMMENTS "
							+ "SET user_id = ?, commentable_id = ?, commentable_type = ?, content = ? "
							+ "WHERE id = ?");
			statement.setInt(5, c.getId());
		}

		if (statement != null) {

			statement.setInt(1, c.getUser().getId());
			statement.setInt(2, c.getOwner().getCommentableId());
			statement.setString(3, c.getOwner().getCommentableType());
			statement.setString(4, c.getContent());

			statement.execute();
		}
	}

	private void deleteComment(final Comment comment) throws Exception {
		final Connection conn = this.dispatcher.getConnection();
		PreparedStatement statement;
		statement = conn.prepareStatement("DELETE FROM COMMENTS WHERE ID = ?");
		statement.setInt(1, comment.getId());
	}

	public void deleteHotel(final Hotel hotel) {
		Connection conn = null;
		try {
			if (hotel.getId() != null) {
				conn = this.dispatcher.getConnection();

				this.updateHotelComments(hotel);

				final PreparedStatement statement = conn
						.prepareStatement("DELETE FROM HOTELS WHERE ID = ?");

				statement.setInt(1, hotel.getId());

				statement.execute();

			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public void saveOrUpdateHotel(final Hotel hotel) {
		Connection conn = null;
		try {
			conn = this.dispatcher.getConnection();
			PreparedStatement statement;
			if (hotel.getId() == null) {
				statement = conn.prepareStatement(
						"INSERT INTO HOTELS(description, name) VALUES (?, ?)",
						PreparedStatement.RETURN_GENERATED_KEYS);

				statement.setString(1, hotel.getDescription());
				statement.setString(2, hotel.getName());
				statement.execute();

				final ResultSet set = statement.getGeneratedKeys();

				set.next();

				try {
					hotel.setId(set.getInt("id"));
				} catch (final Exception e) {
					hotel.setId(set.getInt(1));
				}

				final Collection<Comment> comments = this
						.makeHotelComments(hotel);

				hotel.setComments(comments);
			} else {
				if (hotel.isDirty()) {
					statement = conn
							.prepareStatement("UPDATE HOTELS "
									+ "SET description = ?, name = ? "
									+ "WHERE id = ?");
					statement.setInt(3, hotel.getId());

					statement.setString(1, hotel.getDescription());
					statement.setString(2, hotel.getName());

					statement.execute();
				}

				this.updateHotelComments(hotel);

			}

		} catch (final SQLException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	private void updateHotelComments(final Hotel hotel) throws Exception {
		final DeletionMemoryCollection<Comment> comments = (DeletionMemoryCollection<Comment>) hotel
				.getComments();

		for (final Comment comment : comments) {
			this.saveOrUpdateComment(comment);
		}

		for (final Comment comment : comments.getDeletedItems()) {
			this.deleteComment(comment);
		}
	}

	private Collection<Comment> makeHotelComments(final Hotel hotel) {
		// Makes lazy loading of comments
		Collection<Comment> comments = new LazyCollection<Comment>(
				new CommentFactory(this.dispatcher, hotel));

		// Allows comments to be deleted without checking any query
		comments = new DeletionMemoryCollection<Comment>(comments);
		return comments;
	}

}
