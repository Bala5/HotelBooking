package com.niit.Back.DAO;

import java.util.List;

import com.niit.Back.model.BillingAddress;

public interface BillingAddressDAO {
	
	
	public boolean add(BillingAddress billingAddress);
	public boolean delete(int id);
	BillingAddress show(int id);
	List<BillingAddress>list(int cartId);
	

	
	
	
}
