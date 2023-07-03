package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String email;
	private String password;
	private String fname;
	private String lname;
	private boolean isAdmin;
	private List<Address> addresses = new ArrayList<>();
	private List<Order> orders = new ArrayList<>();
	private List<CreditCard> cards = new ArrayList<>();
	
	
	public User() {
		this.id = null;
		this.username = null;
		this.email= null;
		this.password = null;
		this.fname= null;
		this.lname = null;
		this.isAdmin = false;
		this.addresses = null;
		this.orders = null;
		this.cards = null;
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
	
	public List<Address> getAddresses() {
		return this.addresses;
	}
	
	public void removeAddress(Address address) {
		this.addresses.removeIf(a -> a.getAddressId().equals(address.getAddressId()));
	}
	
	public void addAddress(Address address) {
		addresses.add(address);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + ", fname="
				+ fname + ", lname=" + lname + "]";
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) {
		this.orders.add(order);
	}
	
	public void removeOrder(Order order) {
		this.orders.removeIf(o -> o.getOrderId().equals(order.getOrderId()));
	}

	public List<CreditCard> getCards() {
		return cards;
	}

	public void setCards(List<CreditCard> cards) {
		this.cards = cards;
	}
	
	public void addCard(CreditCard card) {
		cards.add(card);
	}
	
	public void removeCard(CreditCard card) {
		this.cards.removeIf(c -> c.getNumber().equals(card.getNumber()));
	}
}
