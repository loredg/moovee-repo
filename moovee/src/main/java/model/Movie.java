package model;

import org.joda.time.LocalDate;

public class Movie {
	
	private Integer id;
	private String title;
	private String director;
	private String genre;
	private Integer length;
	private Integer releaseYear;
	private Double price;
	private Integer qty;
	private LocalDate addDate;
	
	public Movie() {
		this.id = null;
		this.title = null;
		this.director = null;
		this.genre = null;
		this.length = 0;
		this.releaseYear = 0;
		this.price = 0.0;
		this.qty = 0;
		this.addDate = null;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDirector() {
		return director;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public Integer getLength() {
		return length;
	}
	
	public void setLength(Integer length) {
		this.length = length;
	}
	
	public Integer getReleaseYear() {
		return releaseYear;
	}
	
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Integer getQty() {
		return qty;
	}
	
	public void setQty(Integer qty) { 
		this.qty = qty;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDate getAddDate() {
		return addDate;
	}
	
	public void setAddDate(LocalDate date) {
		this.addDate = date;
	}
	@Override
	public String toString() {
		return "[\n" + title + "\nDirected by: " + director + "\nYear: " + "\nLenght: " + length + "min." + "\nPrice: " + price + "\nCopies available: " + qty + "\n]\n";
	}
	
}
