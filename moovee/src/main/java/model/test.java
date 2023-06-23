package model;

import java.sql.SQLException;
import java.util.List;

public class test {
	
	public static void main(String args[]) throws SQLException {
	
	MovieDAO movieDAO = new MovieDAO();
	Cart cart = new Cart();
	cart.setMovies((List<Movie>)movieDAO.doRetrieveAll("titolo"));
	
	Movie m = movieDAO.doRetrieveByKey("1");
	cart.removeFromCart(m);
	
	
	System.out.println(cart.getMovies());
	System.out.println(cart.getNumberOfMovies());
	System.out.println(cart.getTotalAmount());
	
	}
}
