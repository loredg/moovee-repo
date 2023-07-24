package model;

public class Contains {
	
	private String movieId;
	private String orderId;
	private Integer qty;
	
	public Contains() {
		super();
		this.movieId = null;
		this.orderId = null;
		this.qty = null;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	
	
}
