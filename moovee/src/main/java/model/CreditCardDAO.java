package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

public class CreditCardDAO implements IBeanDAO<CreditCard>{
	
	Connection connection = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	final DateTimeZone jodaTzUTC = DateTimeZone.forID("UTC");

	@Override
	public void doSave(CreditCard bean) throws SQLException {
		
		java.sql.Date sqlDate = new java.sql.Date(bean.getExpiration().toDateTimeAtStartOfDay(jodaTzUTC).getMillis());
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("INSERT INTO CartaDiPagamento(numero, scadenza, cvc, idAccount) VALUES(?, ?, ?, ?)");
			ps.setString(1, bean.getNumber());
			ps.setDate(2, sqlDate);
			ps.setString(3, bean.getCvc());
			ps.setString(4, bean.getUserId());
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
			ps = connection.prepareStatement("DELETE FROM CartaDiPagamento WHERE idAccount = ?");
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
	public CreditCard doRetrieveByKey(String numero) throws SQLException {
		CreditCard card = new CreditCard();
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT * FROM CartaDiPagamento WHERE numero = ?");
			ps.setString(1, numero);
			rs = ps.executeQuery();
			while(rs.next()) {
				card.setNumber(rs.getString("numero"));
				card.setExpiration(new LocalDate(rs.getDate("scadenza")));
				card.setCvc(rs.getString("cvc"));
				card.setUserId(rs.getString("idAccount"));
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
		return card;
	}

	@Override
	public Collection<CreditCard> doRetrieveAll(String order) throws SQLException {
		Collection<CreditCard> cards = new LinkedList<>();
		String selectSQL = "SELECT * FROM CartaDiPagamento";
		if(order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		try {
			connection = DriverManagerConnectionPool.getConnection();
			ps = connection.prepareStatement("SELECT * FROM CartaDiPagamento ORDER BY ");
			rs = ps.executeQuery();
			while(rs.next()) {
				CreditCard card = new CreditCard();
				card.setUserId(rs.getString("idAccount"));
				card.setNumber(rs.getString("numero"));
				card.setExpiration(new LocalDate(rs.getDate("scadenza")));
				cards.add(card);
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
		return cards;
	}
	
public synchronized Collection<CreditCard> doRetrieveByUser(String id) throws SQLException {
	Collection<CreditCard> cards = new LinkedList<>();
	try {
		connection = DriverManagerConnectionPool.getConnection();
		ps = connection.prepareStatement("SELECT * FROM CartaDiPagamento where idAccount = ?");
		ps.setString(1, id);
		
		rs = ps.executeQuery();
		while(rs.next()) {
			CreditCard card = new CreditCard();
			card.setUserId(rs.getString("idAccount"));
			card.setNumber(rs.getString("numero"));
			card.setExpiration(new LocalDate(rs.getDate("scadenza")));
	
			cards.add(card);
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
	return cards;
}

}
