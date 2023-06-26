package model;

import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;

public class Order {
	
	private List<Movie> movies = new ArrayList<>();
	private String userId;
	private Double total;
	private LocalDate date;
	private String orderId;
	
	public Order() {
		super();
		this.movies = null;
		this.userId = null;
		this.total = null;
		this.date = null;
		this.orderId = null;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	

}
