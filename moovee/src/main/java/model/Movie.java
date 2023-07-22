package model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;

import org.joda.time.LocalDate;

public class Movie implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;
	private String director;
	private String genre;
	private Integer length;
	private Integer releaseYear;
	private Double price;
	private Integer qty;
	private LocalDate addDate;
	private InputStream posterStream;
	private byte[] posterBytes;
	private InputStream landscapePosterStream;
	private byte[] landscapePosterBytes;
	
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
		this.posterStream = null;
		this.posterBytes = null;
		this.landscapePosterBytes = null;
		this.landscapePosterStream = null;
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
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public LocalDate getAddDate() {
		return addDate;
	}
	
	public void setAddDate(LocalDate date) {
		this.addDate = date;
	}
	
	public InputStream getPosterStream() {
		return posterStream;
	}

	public void setPosterStream(InputStream posterStream) {
		this.posterStream = posterStream;
	}

	public byte[] getPosterBytes() {
		return posterBytes;
	}

	public void setPosterBytes(byte[] posterBytes) {
		this.posterBytes = posterBytes;
	}
	@Override
	public String toString() {
		return "[\n" + title + "\nDirected by: " + director + "\nYear: " + "\nLenght: " + length + "min." + "\nPrice: " + price + "\nCopies available: " + qty + "\n]\n";
	}

	public InputStream getLandscapePosterStream() {
		return landscapePosterStream;
	}

	public void setLandscapePosterStream(InputStream lanscapePosterStream) {
		this.landscapePosterStream = lanscapePosterStream;
	}

	public byte[] getLandscapePosterBytes() {
		return landscapePosterBytes;
	}

	public void setLandscapePosterBytes(byte[] landscapePosterBytes) {
		this.landscapePosterBytes = landscapePosterBytes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(id, other.id);
	}
	
}
