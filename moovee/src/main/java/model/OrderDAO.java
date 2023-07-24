package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

public class OrderDAO implements IBeanDAO<Order>{
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public void doSave(Order order) throws SQLException {
		final DateTimeZone jodaTzUTC = DateTimeZone.forID("UTC");
		LocalDate date = new LocalDate();
		java.sql.Date sqlDate = new java.sql.Date(date.toDateTimeAtStartOfDay(jodaTzUTC).getMillis());

		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("INSERT INTO ordine(totale, data, idAccount, idIndirizzo) VALUES(?, ?, ?, ?)");
			
			ps.setDouble(1, order.getTotal());
			ps.setDate(2, sqlDate);
			ps.setString(3, order.getUserId());
			ps.setString(4, order.getAddressId());
			ps.executeUpdate();
		
		}finally {
			try {
				if(ps != null) {
					ps.close();
				}
			}finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}

		}
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.doRetrieveByKey(order.getUserId());
		user.addOrder(order);
		
	}

	@Override
	public boolean doDelete(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int result = 0;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("DELETE FROM ordine WHERE id = ?");
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
	public Order doRetrieveByKey(String id) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		
		Order order = new Order();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT * FROM ordine WHERE id = ?");
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				order.setUserId(rs.getString("idAccount"));
				order.setDate(new LocalDate(rs.getDate("data")));
				order.setTotal(rs.getDouble("total"));
				order.setAddressId(rs.getString("idIndirizzo"));
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
		return order;
	}

	@Override
	public Collection<Order> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement ps = null;
		
		Collection<Order> orders = new LinkedList<Order>();
		String selectSQL = "SELECT * FROM ordine";
		
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Order o = new Order();
				
				o.setDate(new LocalDate(rs.getDate("data")));
				o.setTotal(rs.getDouble("totale"));
				o.setOrderId(rs.getString("id"));
				o.setUserId("idAccount");
				o.setAddressId(rs.getString("idIndirizzo"));
				
				orders.add(o);
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
		return orders;
	}

}
