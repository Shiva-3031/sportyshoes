package com.sportyshoesproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoesproj.dao.CategoryDao;
import com.sportyshoesproj.pojo.Category;

@Service
public class CategoryService {
	
	@Autowired
	CategoryDao categoryDao;
	
	public boolean saveCategory(Category category) {
//		category.setCategoryId(Category.getCategoryKeyValue()+1);
//		Category.setCategoryKeyValue(Category.getCategoryKeyValue()+1);
		return categoryDao.saveCategory(category);
	}
	
	public List<Category> getCategoryList() {
		return categoryDao.getCategoryList();
	}
}
