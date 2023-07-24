package model;

import java.io.Serializable;
import java.util.HashMap;

import org.joda.time.LocalDate;

public class Order implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<Movie, Integer> movies = new HashMap<>();
	private String userId;
	private Double total;
	private LocalDate date;
	private String orderId;
	private String addressId;
	Boolean completed;
	
	public Order() {
		super();
		this.movies = null;
		this.userId = null;
		this.total = null;
		this.date = null;
		this.orderId = null;
		this.completed = false;
		this.setAddressId(null);
	}

	public HashMap<Movie, Integer> getMovies() {
		return movies;
	}

	public void setMovies(HashMap<Movie, Integer> movies) {
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
	
	public Boolean isCompleted() {
		return completed;
	}
	
	public void complete() {
		this.completed = true;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

}
