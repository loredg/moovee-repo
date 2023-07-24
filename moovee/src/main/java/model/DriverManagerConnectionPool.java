package model;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool {

	private static List<Connection> freeDbConnections;
	
	private DriverManagerConnectionPool() {
	    throw new IllegalStateException("Utility class");
	  }

	
	static  {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			System.out.println("DB driver not found!");
			System.out.println(e.getMessage());
		}
	}
	
	private static synchronized Connection createDBConnection() throws SQLException{
		String db = "moovee";
		String username = "root";
		String password = "BDsql2023!";
		Connection connection = null;
		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, username, password);
		connection.setAutoCommit(false);
		}catch(SQLException e ) {
			System.out.println("Error:" + e.getMessage());
		}
		
		return connection;
	}
	
	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;
		
		if(!freeDbConnections.isEmpty()) {
			connection = freeDbConnections.get(0);
			freeDbConnections.remove(0);
			
			try {
				if(connection.isClosed()) {
					connection = DriverManagerConnectionPool.getConnection();
				}
			} catch(SQLException e) {
				connection.close();
				connection = getConnection();
			}
		}
		else {
			connection = createDBConnection();
		}
		
		return connection;
	}
	
	public static synchronized void releaseConnection(Connection connection) {
		if(connection!= null) {
			freeDbConnections.add(connection);
		}
	}
	
}
