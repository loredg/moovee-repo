package model;

import java.io.Serializable;

public class Address implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private String address;
	private String province;
	private String town;
	private String zipCode;
	private String region;
	private String state;
	private String addressId;
	
	
	public Address() {
		super();
		this.userId = null;
		this.address = null;
		this.province = null;
		this.town = null;
		this.zipCode = null;
		this.region = null;
		this.state = null;
		this.addressId = null;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getProvince() {
		return province;
	}


	public void setProvince(String province) {
		this.province = province;
	}


	public String getTown() {
		return town;
	}


	public void setTown(String town) {
		this.town = town;
	}


	public String getZipCode() {
		return zipCode;
	}


	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getAddressId() {
		return addressId;
	}


	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}


	@Override
	public String toString() {
		return "Address [address=" + address + ", province=" + province + ", town=" + town + ", zipCode=" + zipCode
				+ ", region=" + region + ", state=" + state + "]";
	}
	
	

}
