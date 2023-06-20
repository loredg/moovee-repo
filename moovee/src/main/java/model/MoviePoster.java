package model;

import java.io.InputStream;

public class MoviePoster {
	
	private InputStream posterStream;
	private byte[] posterBytes;
	private String movieID;
	
	public MoviePoster() {
		this.posterBytes = null;
		this.posterStream = null;
		this.movieID = null;
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

	public String getMovieID() {
		return movieID;
	}
	
	public void setMovieID(String id) {
		this.movieID = id;
	}
	
	@Override
	public String toString() {
		return this.posterStream.toString();
	}
	
}
