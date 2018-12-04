package com.niit.Back.DAO;

import java.util.List;

import com.niit.Back.model.Hotels;



public interface HotelDAO {
	
	 public boolean addHotel(Hotels hotel);
	
	 public boolean deleteHotel(String hotelName);
	 public Hotels showHotel(String hotelName);
	 public List<Hotels> showallHotel();

	public Hotels getHotel(int id);

}
