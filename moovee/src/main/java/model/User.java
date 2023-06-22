package model;

public class User {
	
	private String id;
	private String username;
	private String email;
	private String password;
	private String fname;
	private String lname;
	private boolean isAdmin;
	
	
	
	public User() {
		this.id = null;
		this.username = null;
		this.email= null;
		this.password = null;
		this.fname= null;
		this.lname = null;
		this.isAdmin = false;
	}

	public String getId() {
		return this.id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getFname() {
		return fname;
	}



	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getLname() {
		return lname;
	}



	public void setLname(String lname) {
		this.lname = lname;
	}



	public boolean isAdmin() {
		return isAdmin;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", fname="
				+ fname + ", lname=" + lname + "]";
	}
	

}
