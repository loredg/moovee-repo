package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import org.joda.time.LocalDate;

public class UserDAO implements IBeanDAO<User>{
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	@Override
	public void doSave(User bean) throws SQLException {
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("INSERT INTO account(username, email, password, nome, cognome, admin) VALUES(?, ?, ?, ?, ?, ?)");
			ps.setString(1, bean.getUsername());
			ps.setString(2, bean.getEmail());
			ps.setString(3, bean.getPassword());
			ps.setString(4, bean.getFname());
			ps.setString(5, bean.getLname());
			ps.setBoolean(6, bean.isAdmin());
			
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
	public boolean doDelete(String username) throws SQLException {
		
		int result = 0;
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("DELETE FROM account WHERE username = ?");
			ps.setString(1, username);
			
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
	public User doRetrieveByKey(String id) throws SQLException {
		
		User user = new User();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT * FROM account WHERE id = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFname(rs.getString("nome"));
				user.setLname(rs.getString("cognome"));
				user.setAdmin(rs.getBoolean("admin"));
				user.setId(rs.getString("id"));
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
		return user;
	}

	@Override
	public Collection<User> doRetrieveAll(String order) throws SQLException {
		Collection<User> users = new LinkedList<User>();
		String selectSQL = "SELECT * FROM account";
		
		if(order != null && order.equals("")) {
			selectSQL += "ORDER BY " + order;
		}
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement(selectSQL);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFname(rs.getString("nome"));
				user.setLname(rs.getString("cognome"));
				user.setAdmin(rs.getBoolean("admin"));
				user.setId(rs.getString("id"));
				
				users.add(user);
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
		return users;
	}

	public User doRetrieveByEmail(String email) throws SQLException {
		User user = new User();
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT * FROM account WHERE email = ?");
			ps.setString(1, email);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setFname(rs.getString("nome"));
				user.setLname(rs.getString("cognome"));
				user.setAdmin(rs.getBoolean("admin"));
				user.setId(rs.getString("id"));
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
		return user;
	}
	
	public synchronized void updateCredentials(String credential, String value, String id) throws SQLException {
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("UPDATE account set " + credential + " = ? WHERE id = ?" );
			ps.setString(1, value);
			ps.setString(2, id);
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

} 
