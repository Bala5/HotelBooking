package com.niit.Back.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity

public class BillingAddress {
	
	
	private int payId;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int addressId;
	
	private String userName;
	
	private String addressLine1;
	
	private String addressLine2;
	
	
	@NotEmpty(message = "state cannot be blank")
	private String city;
	
	@NotEmpty(message = "state cannot be blank")
	private String country;
	
	@NotEmpty(message = "state cannot be blank")
	@Size(min = 6,max = 6)
	@Pattern(regexp="[1-9][0-9]{5}", message="must start with 1-9 and have 6 digits")
	private String pincode;
	
	
	


	public int getPayId() {
		return payId;
	}

	public void setPayId(int payId) {
		this.payId = payId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	}

	

	


