package model;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.joda.time.LocalDate;

public class test {
	
	public static void main(String args[]) throws SQLException {
	
	class Film {
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
		
		public Film() {
			this.id = null;
			this.title = null;
			this.director = null;
			this.genre = null;
			this.length = 0;
			this.releaseYear = 0;
			this.price = 0.0;
			this.qty = 0;
			this.addDate = null;
			this.posterBytes = null;
			this.posterStream = null;
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
		
		public void setPosterStream(InputStream posterStream) {
			this.posterStream = posterStream;
		}
		
		public InputStream getPosterStream() {
			return this.posterStream;
		}
		
		public void setPosterBytes(byte[] posterBytes) {
			this.posterBytes = posterBytes;
		}
		
		public byte[] getPosterBytes() {
			return this.posterBytes;
		}
	}
	
	
	
	}
}
