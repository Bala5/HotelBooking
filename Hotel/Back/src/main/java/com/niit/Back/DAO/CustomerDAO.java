package com.niit.Back.DAO;


import java.util.List;

import com.niit.Back.model.Customer;
import com.niit.Back.model.UserCredentials;


public interface CustomerDAO {
	
	public boolean addCustomer(Customer customer);
	public boolean updateCustomer(Customer customer);
	public boolean deleteCustomer(String emailId);
	public Customer showcustomer(String emailId);
    public List<Customer> showallcustomer();
    public UserCredentials showcred(String email);
	public boolean saveorupdatepassword(UserCredentials uc);
	
	

}
