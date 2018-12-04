package com.niit.Back.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Hotels {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int hotelid;
	
	@Column(unique=true,nullable=false)
	@NotEmpty(message="name cannot be blank")
	private String hotelname;
	
	@NotEmpty(message="description cannot be blank")
	private String hoteldescription;
	
	@Min(value=1, message="quantity must be greater than 1")
	private int roomsavailabe;
	
	@Min(value=100, message="price must be greater than 100")
	private float price;
	
	private int catid;
	
	@Transient
	MultipartFile pimage;

	public int getHotelid() {
		return hotelid;
	}

	public void setHotelid(int hotelid) {
		this.hotelid = hotelid;
	}

	public String getHotelname() {
		return hotelname;
	}

	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}

	public String getHoteldescription() {
		return hoteldescription;
	}

	public void setHoteldescription(String hoteldescription) {
		this.hoteldescription = hoteldescription;
	}

	public int getRoomsavailabe() {
		return roomsavailabe;
	}

	public void setRoomsavailabe(int roomsavailabe) {
		this.roomsavailabe = roomsavailabe;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getCatid() {
		return catid;
	}

	public void setCatid(int catid) {
		this.catid = catid;
	}

	public MultipartFile getPimage() {
		return pimage;
	}

	public void setPimage(MultipartFile pimage) {
		this.pimage = pimage;
	}
	
	

	
	
}
	
	
	
	
	