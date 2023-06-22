package model;

import java.sql.SQLException;
import java.util.List;

public class test {
	
	public static void main(String args[]) throws SQLException {
	
	MovieDAO movieDAO = new MovieDAO();
	List<Movie> movies =  (List<Movie>)movieDAO.doRetrieveAll("titolo");
	System.out.println(movies);
	Movie a = new Movie();
	a.setId("1");
	movies.removeIf(m -> m.getId().equals(a.getId()));
	System.out.println(movies);
	
	}
}
