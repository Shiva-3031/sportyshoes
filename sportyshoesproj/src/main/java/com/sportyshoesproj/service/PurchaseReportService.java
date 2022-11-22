package com.sportyshoesproj.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoesproj.dao.CategoryDao;
import com.sportyshoesproj.dao.ProductDao;
import com.sportyshoesproj.dao.PurchaseReportDao;
import com.sportyshoesproj.dao.UserDao;
import com.sportyshoesproj.pojo.Category;
import com.sportyshoesproj.pojo.Product;
import com.sportyshoesproj.pojo.PurchaseReport;
import com.sportyshoesproj.pojo.User;

@Service
public class PurchaseReportService {
	
	@Autowired
	PurchaseReportDao purchaseReportDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	ProductService productDao;
	
	public List<PurchaseReport> getProductReportList(){
		List<PurchaseReport> list = purchaseReportDao.getAllPurchaseReport();
		System.out.println(list);
		List<User> userList = userDao.getUserList();
		List<Product> productList = productDao.getProductList();
		
		for(PurchaseReport p : list) {
			for(User user: userList) {
				if(p.getPurchaseUserId() == user.getUserId()) {
					p.setPurchaseUsername(user.getUsername());
					break;
				}
			}
			for(Product product: productList) {
				if(p.getPurchaseProductId() == product.getProductId()) {
					p.setProduct(product);
					break;
				}
			}
		}
		return list;		
	}
	
	public List<PurchaseReport> getProductReportListByDate(String date){
		List<PurchaseReport> list = purchaseReportDao.getAllPurchaseReportByDate(date);
		List<User> userList = userDao.getUserList();
		List<Product> productList = productDao.getProductList();
		for(PurchaseReport p : list) {
			if(p != null) {
				for(User user: userList) {
					if(p.getPurchaseUserId() == user.getUserId()) {
						p.setPurchaseUsername(user.getUsername());
						break;
					}
				}
				for(Product product: productList) {
					if(p.getPurchaseProductId() == product.getProductId()) {
						p.setProduct(product);
						break;
					}
				}
			}
			System.out.println(p.toString());
		}
		return list;
	}
	
	public List<PurchaseReport> getProductReportListByCategory(int id){
//		List<PurchaseReport> list = purchaseReportDao.getAllPurchaseReportByCategory(id);
		List<PurchaseReport> list = getProductReportList();
		List<PurchaseReport> finallist = new ArrayList<>();
		List<User> userList = userDao.getUserList();
		List<Product> productList = productDao.getProductList();
		
		for(PurchaseReport p : list) {
			
			for(User user: userList) {
				if(p.getPurchaseUserId() == user.getUserId()) {
					p.setPurchaseUsername(user.getUsername());
					break;
				}
			}
			for(Product product: productList) {
				if(p.getPurchaseProductId() == product.getProductId()) {
					p.setProduct(product);
					break;
				}
			}
		}	
		
		for(PurchaseReport p:list) {
			if(p.getProduct().getProductCategoryId() == id) {
				finallist.add(p);
			}
		}
		return finallist;
	}
	
}
