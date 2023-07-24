package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ContainsDAO implements IBeanDAO<Contains>{
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public void doSave(Contains bean) throws SQLException {

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("INSERT INTO composto_da(idFilm, idOrdine) VALUES(?, ?)");
			
			ps.setString(1, bean.getMovieId());
			ps.setString(2, bean.getOrderId());
			
			ps.executeUpdate();
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
		
	}

	@Override
	public boolean doDelete(String id) throws SQLException {
int result = 0;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("DELETE FROM composto_da WHERE id = ?");
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
		return(result != 0);
	}

	@Override
	public Contains doRetrieveByKey(String id) throws SQLException {
		Contains contains = new Contains();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT * FROM composto_da WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				contains.setMovieId(rs.getString("idFilm"));
				contains.setOrderId(rs.getString("idOrdine"));
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
		
		return contains;
	}

	@Override
	public Collection<Contains> doRetrieveAll(String order) throws SQLException {
		Collection<Contains> containsList = new LinkedList<Contains>();
		String selectSQL = "SELECT * FROM composto_da";
		
		if(order != null && order.equals("")) {
			selectSQL += "ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Contains contains = new Contains();
				contains.setMovieId(rs.getString("idFilm"));
				contains.setOrderId(rs.getString("idOrdine"));
				
				containsList.add(contains);
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
		return containsList;
	}
}
