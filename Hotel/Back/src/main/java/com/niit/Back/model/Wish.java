package com.niit.Back.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Wish {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int paymentId;
	private int hid;
	private String hname;
	private double hprice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public double getHprice() {
		return hprice;
	}
	public void setHprice(double hprice) {
		this.hprice = hprice;
	}
	
	
	

}
