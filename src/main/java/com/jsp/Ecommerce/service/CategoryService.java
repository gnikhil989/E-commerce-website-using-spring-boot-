package com.jsp.Ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.Ecommerce.dao.CategoryRepositry;
import com.jsp.Ecommerce.dto.Category;

@Service
public class CategoryService {

	@Autowired
	CategoryRepositry categoryRepositry;
	public void createCategory(Category category) {
		 categoryRepositry.save(category);
	}
	public List<Category> getCategoryList() {
		return categoryRepositry.findAll();
	}
	public Category updateCategory(Integer id, Category category) {
		// TODO Auto-generated method stub
		Category category2=categoryRepositry.findById(id).get();
		category2.setDescription(category.getDescription());
		category2.setCategoryname(category.getCategoryname());
		return categoryRepositry.save(category2);
	}
	public Category getCategoryById(Integer id) {
		
		 Category category= categoryRepositry.findById(id).get();
		 return category;
	}
	

}
