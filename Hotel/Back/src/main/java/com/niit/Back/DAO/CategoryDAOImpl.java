package com.niit.Back.DAO;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Back.model.Category;


@Repository("categorydao")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	SessionFactory sf;

	public boolean addCategory(Category category) {
		try {
			sf.getCurrentSession().saveOrUpdate(category);
			return true;
		} catch (Exception e) {
			return false;
		}
	}



	public List<Category> showAllCategory() {
		try {
			List<Category> category = (List<Category>) sf.getCurrentSession().createQuery("from Category").list();
			return category;
		} catch (Exception e) {
			return null;
		}
	}

	public boolean deleteCategory(String categoryName) {
		try {
			sf.getCurrentSession().delete(showCategory(categoryName));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Category showCategory(String categoryName) {
		try {
			Category category = (Category) sf.getCurrentSession().
					createQuery("from Category where categoryName='"+categoryName+"'").uniqueResult();
			return category;
		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}	}

}
