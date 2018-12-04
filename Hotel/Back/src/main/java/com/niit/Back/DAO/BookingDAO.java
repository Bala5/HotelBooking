package com.niit.Back.DAO;

import java.util.List;

import com.niit.Back.model.CustomerBookings;



public interface BookingDAO {

	boolean insert(CustomerBookings booking) ;
		


	List<CustomerBookings>viewAllbooking(int payId);

	List<CustomerBookings>viewreceipt(String orderid);
}
