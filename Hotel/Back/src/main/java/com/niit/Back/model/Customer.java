package com.niit.Back.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Paymentid;

	@Column(unique = true, nullable = false)
	@NotEmpty(message = "email cannot be blank")
	@Email(regexp="[a-z0-9_]+@[a-z]+\\.[a-z]{2,3}",message="please enter a valid email id")
	private String emailId;
	

	@Column
	@NotEmpty(message="password should not be empty")
	private String name;

	
	@Transient
	@NotEmpty(message = "password cannot be blank")
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}", message="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters")
	private String password;
	
	
	@Column(unique = true, nullable = false)
	@NotEmpty(message = "phone number cannot be blank")
	@Size(min = 10, max = 10)
	@Pattern(regexp="[9876][0-9]{9}", message="must start with 9876 and have 10 numbers")
	private String phoneNo;



	public void setPhoneNo(String phoneNo)
	{
		
		this.phoneNo = phoneNo;
	}



	public int getPaymentid() {
		return Paymentid;
	}



	public void setPaymentid(int Paymentid) {
		this.Paymentid = Paymentid;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	


	public String getPhoneNo() {
		return phoneNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
