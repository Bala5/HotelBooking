package com.niit.Back.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserCredentials {

	@Id
	
	private String emailId;
	private String password;
	private String role;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
	
	
	