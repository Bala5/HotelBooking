package com.niit.Back.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Back.model.Wish;

@Repository("wishDAO")
@Transactional

public class WishDAOImpl implements WishDAO {
	
	
	@Autowired
	SessionFactory sf;

	private static List<Wish> wishlist = new ArrayList<>();
	
	Wish wish= new Wish();
	

	@Override
	public boolean add(Wish wish) {
		try {
			sf.getCurrentSession().saveOrUpdate(wish);
		
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();

			return false;	
		}
	}


	@Override
	public boolean delete(int id) {
try {
			
			wish = sf.getCurrentSession().get(Wish.class, id);
			sf.getCurrentSession().delete(wish);
		
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();

			return false;	
		}

	}

	@Override
	public Wish get(int id){
		wish = sf.getCurrentSession().get(Wish.class, id);
		return wish;
	}


	@Override
	public List<Wish> show(int Payid) {
		try {
			wishlist = (List<Wish>) sf.getCurrentSession().createQuery("from Wish where cartId="+Payid).list();
			return wishlist;
		} catch (Exception e) {
			return null;
		}
	}


}
