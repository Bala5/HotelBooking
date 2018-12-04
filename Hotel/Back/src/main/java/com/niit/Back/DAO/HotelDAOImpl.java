package com.niit.Back.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Back.model.Hotels;

@Repository("hotelDAO")
@Transactional
public class HotelDAOImpl implements HotelDAO {

	@Autowired
	SessionFactory sf;




	@Override
	public boolean deleteHotel(String hotelName) {
		try {
			sf.getCurrentSession().delete(showHotel(hotelName));
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}


	@Override
	public Hotels showHotel(String hotelName) {
		try {
			Hotels product=(Hotels)sf.getCurrentSession().createQuery("from Hotels where hotelName='"+hotelName+"'").uniqueResult();
			return product;
		}

		catch (Exception e) {
			return null;
		}

	}


	@Override
	public List<Hotels> showallHotel() 
	{
		try {

			@SuppressWarnings("unchecked")
			ArrayList<Hotels> product = (ArrayList<Hotels>) sf.getCurrentSession().createQuery("from Hotels").list();
			System.out.println(product.isEmpty());
			return product;
		} catch (Exception e) {
			System.out.println(e.getMessage());

			return null;
		}
	}


	@Override
	public Hotels getHotel(int hotelid) {
		Hotels hotel = sf.getCurrentSession().get(Hotels.class,hotelid );
		
		return hotel;
	}


	@Override
	public boolean addHotel(Hotels hotel) {
		try {
			sf.getCurrentSession().saveOrUpdate(hotel);;
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		}
	}




	
	}

