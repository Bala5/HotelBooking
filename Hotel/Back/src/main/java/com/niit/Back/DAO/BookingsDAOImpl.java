package com.niit.Back.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Back.model.CustomerBookings;


@Repository("orderDAO")
@Transactional

public class BookingsDAOImpl implements BookingDAO {

	@Autowired
	SessionFactory sessionFactory;

	private static List<CustomerBookings> bookinglist = new ArrayList<>();

	CustomerBookings order = new CustomerBookings();


	@Override
	public List<CustomerBookings> viewreceipt(String bookingid) {
		try {
			bookinglist = (List<CustomerBookings>) sessionFactory.getCurrentSession()
					.createQuery("from CustomerBookings where bookingid='" + bookingid + "'").list();
			return bookinglist;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean insert(CustomerBookings booking) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(booking);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CustomerBookings> viewAllbooking(int payId) {
		try {
			bookinglist = (List<CustomerBookings>) sessionFactory.getCurrentSession()
					.createQuery("from CustomerOrder where cartId=" + payId).list();
			return bookinglist;
		} catch (Exception e) {
			return null;
		}
	}

}
