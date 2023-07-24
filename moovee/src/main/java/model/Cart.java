package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Cart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Movie, Integer> movies;
	private Integer numberOfMovies;
	private Double totalAmount;

	public Cart() {
		this.movies = new HashMap<>();
		this.numberOfMovies = 0;
		this.totalAmount = 0.0;
	}

	public HashMap<Movie, Integer> getMovies() {
		return movies;
	}

	public void setMovies(HashMap<Movie, Integer> movies) {
		this.movies = movies;
	}

	public Integer getNumberOfMovies() {
		return numberOfMovies;
	}

	public void setNumberOfMovies(Integer numberOfMovies) {
		this.numberOfMovies = numberOfMovies;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalPrice) {
		this.totalAmount = totalPrice;
	}

	public void addToCart(Movie movie) {
		if (movies.containsKey(movie)) {
			Integer qty = movies.get(movie);
			movies.put(movie, qty + 1);
		} else {
			movies.put(movie, 1);
		}
		this.setNumberOfMovies(this.getNumberOfMovies() + 1);
		this.setTotalAmount(this.getTotalAmount() + movie.getPrice());
	}

	public void removeFromCart(Movie movie) {
		Integer qty = movies.get(movie);
		Iterator<Movie> it = movies.keySet().iterator();
		while (it.hasNext()) {
			if (it.next().getId().equals(movie.getId())) {
				it.remove();
			}
		}
		if (qty != null) {
			this.setNumberOfMovies(this.getNumberOfMovies() - qty);
			this.setTotalAmount(this.getTotalAmount() - (movie.getPrice() * qty));
		}
	}
	
	public void changeQty(Movie m, Integer qty) {
		this.getMovies().put(m, qty);
		Double total = 0.0;
		int n = 0;
		for(Movie movie : movies.keySet()) {
			int q = movies.get(movie);
			total += movie.getPrice() * q;
			n += q; 
		}
		this.setTotalAmount(total);
		this.setNumberOfMovies(n);
	}

}
