package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import org.joda.time.LocalDate;

public class PosterDAO implements IBeanDAO<MoviePoster>{
	
	private Connection connection = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public void doSave(MoviePoster bean) throws SQLException {
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("UPDATE film SET copertina = ? WHERE id = ?");
			ps.setBinaryStream(1, bean.getPosterStream(), bean.getPosterStream().available());
			ps.setString(2, bean.getMovieID());
			ps.executeUpdate();
			connection.commit();
		}catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}finally {
				if(connection != null) {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
		
	}

	@Override
	public boolean doDelete(String id) throws SQLException {
		int result = 0;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("DELETE copertina FROM film WHERE id = ?");
			ps.setString(1, id);
			result = ps.executeUpdate();
			connection.commit();
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return (result != 0);
	}

	@Override
	public MoviePoster doRetrieveByKey(String id) throws SQLException {
		MoviePoster poster = new MoviePoster();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT copertina FROM film WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				poster.setMovieID(id);
				poster.setPosterBytes(rs.getBytes("copertina"));
			}
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}catch(SQLException e) {
				System.out.println("ERROR: " + e.getMessage());
			}finally {
				if(connection != null) {
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
		}
			return poster;
	}

	@Override
	public Collection<MoviePoster> doRetrieveAll(String order) throws SQLException {
		Collection<MoviePoster> posters = new LinkedList<>();
		String selectSQL = "SELECT * FROM film";
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MoviePoster poster = new MoviePoster();
				poster.setMovieID(rs.getString("id"));
				poster.setPosterStream(rs.getBinaryStream("copertina"));
				posters.add(poster);
			}
			
		
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return posters;
	}

	@Override
	public Collection<MoviePoster> doRetrieveSinceDate(LocalDate date) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
