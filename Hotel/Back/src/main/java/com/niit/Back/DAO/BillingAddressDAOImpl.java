package com.niit.Back.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Back.model.BillingAddress;

@Repository("addressdao")
@Transactional

public class BillingAddressDAOImpl implements BillingAddressDAO {

	@Autowired
	SessionFactory sessionFactory;

	private static List<BillingAddress> addresslist = new ArrayList<>();

	BillingAddress address = new BillingAddress();

	@Override
	public boolean add(BillingAddress billingAddress) {

		try {
			sessionFactory.getCurrentSession().saveOrUpdate(billingAddress);

			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}
	}

	@Override
	public boolean delete(int id) {

		try {
			address = sessionFactory.getCurrentSession().get(BillingAddress.class, id);
			sessionFactory.getCurrentSession().delete(address);

			return true;
		} catch (Exception e) {
			e.printStackTrace();

			return false;
		}

	}

	@Override
	public BillingAddress show(int id) {

		address = sessionFactory.getCurrentSession().get(BillingAddress.class, id);

		return address;
	}

	@Override
	public List<BillingAddress> list(int cartId) {
		try {
			addresslist = (List<BillingAddress>) sessionFactory.getCurrentSession()
					.createQuery("from BillingAddress where cartId=" + cartId).list();
			return addresslist;
		} catch (Exception e) {
			return null;
		}

	}
}
