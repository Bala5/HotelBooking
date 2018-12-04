package com.niit.Back.DAO;

	import java.util.List;

import com.niit.Back.model.Category;


	
	public interface CategoryDAO {
		

	
		public boolean addCategory(Category category);
		public List<Category> showAllCategory();
		public boolean deleteCategory(String categoryName);
		public Category showCategory(String categoryName);
		
		

	}