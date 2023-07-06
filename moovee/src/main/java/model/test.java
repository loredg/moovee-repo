package model;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class test {
	
	public static void main(String args[]) throws SQLException {
	
	
	MovieDAO movieDAO = new MovieDAO();
	Collection<?> movies = (Collection<?>)movieDAO.doRetrieveByTitle("b");
	Iterator<?> it = movies.iterator();
	
	if(movies.isEmpty()) {
		System.out.println("null");
	}
	
	while(it.hasNext()) {
		Movie movie = (Movie) it.next();
		System.out.println(movie.getTitle());
	}
	
	
	}
}
