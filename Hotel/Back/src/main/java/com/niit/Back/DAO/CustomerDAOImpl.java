package com.niit.Back.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Back.model.Customer;
import com.niit.Back.model.UserCredentials;



@Repository("CustomerDAO")
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	SessionFactory sf;

	@Override
	public boolean addCustomer(Customer customer) {
		try {
			UserCredentials u= new UserCredentials();
			u.setEmailId(customer.getEmailId());
			u.setPassword(customer.getPassword());
			u.setRole("ROLE_USER");
			sf.getCurrentSession().saveOrUpdate(customer);
			sf.getCurrentSession().saveOrUpdate(u);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		}
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		try {
			sf.getCurrentSession().update(customer);
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		}

	}

	@Override
	public boolean deleteCustomer(String emailId) {
		try {
			sf.getCurrentSession().delete(showcustomer(emailId));
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		}

	}

	@Override
	public Customer showcustomer(String emailId) {
		try {
			Customer customer = (Customer) sf.getCurrentSession()
					.createQuery("from Customer where emailId= '" + emailId + "'").uniqueResult();
			return customer;
		} catch (Exception e) {
			Customer customer = null;
			System.out.println(e.getMessage());
			return customer;
		}
	}

	@Override
	public List<Customer> showallcustomer()
	{
		try
		{
			
			ArrayList<Customer> customer=(ArrayList<Customer>)sf.getCurrentSession().createQuery("from Customer").list();
		return customer;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@Override
	public UserCredentials showcred(String email) {
		try {
			UserCredentials customer= (UserCredentials) sf.getCurrentSession().createQuery("from UserCredentials where emailId ='"+email+"'").uniqueResult();
			
			return customer;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean saveorupdatepassword(UserCredentials uc) {
		try
		{
			sf.getCurrentSession().saveOrUpdate(uc);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}


}
