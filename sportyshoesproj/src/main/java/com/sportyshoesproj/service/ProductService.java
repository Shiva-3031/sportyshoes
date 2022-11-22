package com.sportyshoesproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoesproj.dao.CategoryDao;
import com.sportyshoesproj.dao.ProductDao;
import com.sportyshoesproj.pojo.Category;
import com.sportyshoesproj.pojo.Product;

@Service
public class ProductService {
	
	@Autowired
	ProductDao productDao;
	@Autowired
	CategoryDao categoryDao;

	public boolean saveProduct(Product product) {
		return productDao.saveProduct(product);
	}
	
	public List<Product> getProductList(){
		List<Product> productList = productDao.getProductList();
		List<Category> categoryList = categoryDao.getCategoryList();
		for(Product product : productList) {
			String categoryName = null;
			for(Category category: categoryList) {
				if(category.getCategoryId() == product.getProductCategoryId()) {
					categoryName = category.getCategoryName();
					break;
				}
			}
			product.setProductCategoryName(categoryName);
		}
		return productList;
	}
	
	public boolean deleteProduct(int id) {
		return productDao.deleteProduct(id);
	}
	
	public Product getProductById(int id) {
		return productDao.getProductById(id).get();
	}
	
	public boolean updateProduct(Product product) {
		return productDao.updateProduct(product);
	}
}
