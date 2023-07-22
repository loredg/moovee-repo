package model;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import org.joda.time.LocalDate;
import org.joda.time.DateTimeZone;

public class MovieDAO implements IBeanDAO<Movie> {

	private static final String TABLE_NAME = "film";
	final DateTimeZone jodaTzUTC = DateTimeZone.forID("UTC");

	@Override
	public synchronized void doSave(Movie movie) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		LocalDate today = new LocalDate();
		java.sql.Date sqlDate = new java.sql.Date(today.toDateTimeAtStartOfDay(jodaTzUTC).getMillis());

		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ "(regista, genere, anno_uscita, durata_min, prezzo, qta, titolo, data_aggiunta, copertina_landscape) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(insertSQL);

			ps.setString(7, movie.getTitle());
			ps.setString(1, movie.getDirector());
			ps.setString(2, movie.getGenre());
			ps.setInt(4, movie.getLength());
			ps.setInt(3, movie.getReleaseYear());
			ps.setDouble(5, movie.getPrice());
			ps.setInt(6, movie.getQty());
			ps.setDate(8, sqlDate);
			ps.setBinaryStream(9, movie.getLandscapePosterStream());

			ps.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}

		}

	}

	@Override
	public synchronized boolean doDelete(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int result = 0;

		String deleteSQL = "UPDATE film SET qta = -1 WHERE id = ?";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(deleteSQL);
			ps.setString(1, id);

			result = ps.executeUpdate();
			connection.commit();

		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Movie doRetrieveByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		Movie movie = new Movie();

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT * FROM film WHERE id = ?");
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				movie.setId(rs.getString("id"));
				movie.setTitle(rs.getString("titolo"));
				movie.setDirector(rs.getString("regista"));
				movie.setGenre(rs.getString("genere"));
				movie.setLength(rs.getInt("durata_min"));
				movie.setReleaseYear(rs.getInt("anno_uscita"));
				movie.setPrice(rs.getDouble("prezzo"));
				movie.setQty(rs.getInt("qta"));
				movie.setAddDate(new LocalDate(rs.getDate("data_aggiunta")));
				movie.setPosterBytes(rs.getBytes("copertina"));
				movie.setLandscapePosterBytes(rs.getBytes("copertina_landscape"));
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return movie;
	}

	@Override
	public synchronized Collection<Movie> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		Collection<Movie> movies = new LinkedList<Movie>();
		String selectSQL = "SELECT * FROM " + TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Movie movie = new Movie();

				movie.setId(rs.getString("id"));
				movie.setTitle(rs.getString("titolo"));
				movie.setDirector(rs.getString("regista"));
				movie.setGenre(rs.getString("genere"));
				movie.setLength(rs.getInt("durata_min"));
				movie.setReleaseYear(rs.getInt("anno_uscita"));
				movie.setPrice(rs.getDouble("prezzo"));
				movie.setQty(rs.getInt("qta"));
				movie.setAddDate(new LocalDate(rs.getDate("data_aggiunta")));
				movie.setPosterBytes(rs.getBytes("copertina"));
				movie.setLandscapePosterBytes(rs.getBytes("copertina_landscape"));

				movies.add(movie);
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return movies;
	}

	public synchronized Collection<Movie> doRetrieveSinceDate(LocalDate date) throws SQLException {
		Collection<Movie> movies = new LinkedList<Movie>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE data_aggiunta > ?");
			java.sql.Date sqlDate = new java.sql.Date(date.toDateTimeAtStartOfDay(jodaTzUTC).getMillis());
			ps.setDate(1, sqlDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				Movie movie = new Movie();
				movie.setId(rs.getString("id"));
				movie.setTitle(rs.getString("titolo"));
				movie.setDirector(rs.getString("regista"));
				movie.setGenre(rs.getString("genere"));
				movie.setLength(rs.getInt("durata_min"));
				movie.setReleaseYear(rs.getInt("anno_uscita"));
				movie.setPrice(rs.getDouble("prezzo"));
				movie.setQty(rs.getInt("qta"));
				movie.setPosterBytes(rs.getBytes("copertina"));
				movie.setAddDate(new LocalDate(rs.getDate("data_aggiunta")));
				movie.setLandscapePosterBytes(rs.getBytes("copertina_landscape"));

				movies.add(movie);
			}

		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return movies;
	}
	
	public synchronized void addLandscapePoster(InputStream landscapePoster, String id) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("UPDATE film SET copertina_landscape = ? where id = ?");
			ps.setBinaryStream(1, landscapePoster);
			ps.setString(2, id);
			ps.executeUpdate();
			connection.commit();
		}finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
	public synchronized Collection<Movie> doRetrieveByTitle(String title) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<Movie> movies = new LinkedList<>();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT * FROM film WHERE titolo LIKE '%" + title + "%'");
			rs = ps.executeQuery();
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setId(rs.getString("id"));
				movie.setTitle(rs.getString("titolo"));
				movie.setDirector(rs.getString("regista"));
				movie.setGenre(rs.getString("genere"));
				movie.setLength(rs.getInt("durata_min"));
				movie.setReleaseYear(rs.getInt("anno_uscita"));
				movie.setPrice(rs.getDouble("prezzo"));
				movie.setQty(rs.getInt("qta"));
				movie.setAddDate(new LocalDate(rs.getDate("data_aggiunta")));
				movie.setPosterBytes(rs.getBytes("copertina"));
				movie.setLandscapePosterBytes(rs.getBytes("copertina_landscape"));

				movies.add(movie);
			}
			
		}finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return movies;
	}
	
	public synchronized Collection<Movie> doRetrieveByFilter(String filter, String filterValue) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<Movie> movies = new LinkedList<>();
		String sql = null;
		
		if(filter.equals("titolo") || filter.equals("genere")) {
			sql = "SELECT * FROM film WHERE " + filter + " LIKE '%" + filterValue + "%'";
		}
		else {
			sql = "SELECT * FROM film WHERE " + filter + " <= " + filterValue;
		}
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Movie movie = new Movie();
				movie.setId(rs.getString("id"));
				movie.setTitle(rs.getString("titolo"));
				movie.setDirector(rs.getString("regista"));
				movie.setGenre(rs.getString("genere"));
				movie.setLength(rs.getInt("durata_min"));
				movie.setReleaseYear(rs.getInt("anno_uscita"));
				movie.setPrice(rs.getDouble("prezzo"));
				movie.setQty(rs.getInt("qta"));
				movie.setAddDate(new LocalDate(rs.getDate("data_aggiunta")));
				movie.setPosterBytes(rs.getBytes("copertina"));
				movie.setLandscapePosterBytes(rs.getBytes("copertina_landscape"));

				movies.add(movie);
			}
			
		}finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return movies;
	}

}
