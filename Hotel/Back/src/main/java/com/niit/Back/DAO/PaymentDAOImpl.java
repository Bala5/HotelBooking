package com.niit.Back.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Back.model.Payment;


@Repository("PaymentDAO")
@Transactional
public class PaymentDAOImpl implements PaymentDAO {

	@Autowired
	SessionFactory sessionFactory;

	private static List<Payment> hotelitems = new ArrayList<>();
		
	Payment payment= new Payment();
	

	@Override
	public boolean delete(int id) {
		try {
			 payment = sessionFactory.getCurrentSession().get(Payment.class, id);
					 sessionFactory.getCurrentSession().delete(id);
		
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();

			return false;	
		}

	}

	@Override
	public boolean add(Payment payment) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(payment);
		
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();

			return false;	
		}

	}

	@Override
	public List<Payment> show(int Paymentid) {
		try {
			hotelitems = (List<Payment>) sessionFactory.getCurrentSession().createQuery("from Payment where Paymentid="+Paymentid).list();
			return hotelitems;
		} catch (Exception e) {
			return null;
		}
	}

	
	
}