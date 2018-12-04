package com.niit.Back.DAO;

import java.util.List;

import com.niit.Back.model.Wish;

public interface WishDAO {
	
	boolean add(Wish wish);
	boolean delete(int id);
	Wish get(int id);
	List<Wish> show(int Payid);


}
