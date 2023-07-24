package model;

import org.joda.time.LocalDate;

public class CreditCard {
	
	private String number;
	private LocalDate expiration;
	private String cvc;
	private String userId;
	
	public CreditCard() {
		super();
		this.number = null;
		this.expiration = null;
		this.cvc = null;
		this.userId = null;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public LocalDate getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
