package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cart implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Movie> movies;
	private Integer numberOfMovies;
	private Double totalAmount;
	
	public Cart() {
		this.movies = new ArrayList<>();
		this.numberOfMovies = 0;
		this.totalAmount = 0.0;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Integer getNumberOfMovies() {
		return this.movies.size();
	}

	public void setNumberOfMovies(Integer numberOfMovies) {
		this.numberOfMovies = numberOfMovies;
	}

	public Double getTotalAmount() {
		Double totalPrice = 0.0;
		for(Movie m : movies) {
			totalPrice += m.getPrice();
		}
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalAmount = totalPrice;
	}
	
	public void addToCart(Movie movie) {
		this.movies.add(movie);
		setNumberOfMovies(getNumberOfMovies());
		setTotalPrice(getTotalAmount());
	}
	
	public void removeFromCart(Movie movie) {
		this.movies.removeIf(m -> m.getId().equals(movie.getId()));
		setNumberOfMovies(getNumberOfMovies());
		setTotalPrice(getTotalAmount());
	}

}
