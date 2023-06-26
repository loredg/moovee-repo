package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class AddressDAO implements IBeanDAO<Address>{
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public void doSave(Address bean) throws SQLException {
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("INSERT INTO indirizzo(idAccount, via, cap, citta, provincia, regione, stato) VALUES(?, ?, ?, ?, ?, ?, ?");
			
			ps.setString(1, bean.getUserId());
			ps.setString(2, bean.getAddress());
			ps.setString(3, bean.getZipCode());
			ps.setString(4, bean.getTown());
			ps.setString(5, bean.getProvince());
			ps.setString(6, bean.getRegion());
			ps.setString(7, bean.getState());
			
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
			ps = connection.prepareStatement("DELETE FROM indirizzo WHERE id = ?");
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
	public Address doRetrieveByKey(String id) throws SQLException {
		Address address = new Address();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT * FROM indirizzo WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				address.setUserId(rs.getString("idAccount"));
				address.setAddress(rs.getString("via"));
				address.setZipCode(rs.getString("cap"));
				address.setTown(rs.getString("citta"));
				address.setProvince(rs.getString("provincia"));
				address.setRegion(rs.getString("regione"));
				address.setState(rs.getString("stato"));
				address.setAddressId(rs.getString("id"));
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
		return address;
	}

	@Override
	public Collection<Address> doRetrieveAll(String order) throws SQLException {
		Collection<Address> addresses = new LinkedList<Address>();
		String selectSQL = "SELECT * FROM indirizzo";
		
		if(order != null && order.equals("")) {
			selectSQL += "ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Address address = new Address();
				address.setUserId(rs.getString("idAccount"));
				address.setAddress(rs.getString("via"));
				address.setZipCode(rs.getString("cap"));
				address.setTown(rs.getString("citta"));
				address.setProvince(rs.getString("provincia"));
				address.setRegion(rs.getString("regione"));
				address.setState(rs.getString("stato"));
				address.setAddressId(rs.getString("id"));
				
				addresses.add(address);
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
		return addresses;
	}
	
	public Address doRetrieveByAddress(String address) throws SQLException {
		Address a = new Address();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT * FROM indirizzo WHERE address = ?");
			ps.setString(1, address);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				a.setUserId(rs.getString("idAccount"));
				a.setAddress(rs.getString("via"));
				a.setZipCode(rs.getString("cap"));
				a.setTown(rs.getString("citta"));
				a.setProvince(rs.getString("provincia"));
				a.setRegion(rs.getString("regione"));
				a.setState(rs.getString("stato"));
				a.setAddressId(rs.getString("id"));
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
		return a;
	}

}
