package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Cart {
	
	private List<Movie> movies = new ArrayList<>();
	private Integer numberOfMovies;
	private Double totalPrice;
	
	public Cart() {
		this.movies = null;
		this.numberOfMovies = null;
		this.totalPrice = null;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Integer getNumberOfMovies() {
		return numberOfMovies;
	}

	public void setNumberOfMovies(Integer numberOfMovies) {
		this.numberOfMovies = numberOfMovies;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public void addToCart(Movie movie) {
		this.movies.add(movie);
	}
	
	public void removeFromCart(Movie movie) {
		this.movies.removeIf(m -> m.getId().equals(movie.getId()));
	}

}
