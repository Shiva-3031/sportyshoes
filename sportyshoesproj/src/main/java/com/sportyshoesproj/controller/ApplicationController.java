package com.sportyshoesproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoesproj.pojo.Category;
import com.sportyshoesproj.pojo.Product;
import com.sportyshoesproj.pojo.User;
import com.sportyshoesproj.service.CategoryService;
import com.sportyshoesproj.service.LoginService;
import com.sportyshoesproj.service.ProductService;
import com.sportyshoesproj.service.PurchaseReportService;
import com.sportyshoesproj.service.UserService;

@Controller
@RequestMapping("/")
public class ApplicationController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	UserService userService;
	@Autowired
	PurchaseReportService purchaseReportService;
	@Autowired
	LoginService loginService;
	
	@GetMapping("/")
	public String indexPage() {
		return "index";
	}
	
	@GetMapping("/adminpage")
	public String adminPage() {
		return "adminpage";
	}
	
	@GetMapping("/categoryform")
	public String categoryPage(Model model) {
		model.addAttribute("category", new Category());
		return "categoryform";
	}
	
	@GetMapping("/productform")
	public String productPage(Model model) {
		model.addAttribute("product", new Product());
		List<Category> categoryList = categoryService.getCategoryList();
		System.out.println(categoryList.toString());
		model.addAttribute("categoryList", categoryList);
		return "productform";
	}
	
	@GetMapping("/productlist")
	public String productList(Model model) {
		List<Product> productList = productService.getProductList();
		model.addAttribute("productList", productList);	
		model.addAttribute("editproduct", new Product());
		return "productpage";
	}
	
	@GetMapping("/editproductform/{productid}")
	public String editProductForm(@PathVariable("productid") Integer productId, Model model) {
		Product product = productService.getProductById(productId);
		model.addAttribute("product", product);
		List<Category> list = categoryService.getCategoryList();
		model.addAttribute("categoryList", list);
		System.out.println(product.toString());
		return "editproductformpage";
	}
	
	@GetMapping("/deleteproductform/{productid}")
	public String deleteProductForm(@PathVariable("productid") Integer productId) {
		productService.deleteProduct(productId);
		return "adminpage";
	}
	
	@GetMapping("/searchuserform")
	public String searchUserForm(Model model) {
		model.addAttribute("userlist", userService.getUserList());
		System.out.println("get search user completed successfully");
		return "usersearchform";
	}
	
	@GetMapping("/purchasereport")
	public String purchaseReportPage() {
		return "purchasereportpage";
	}
	
	@GetMapping("/purchasereportbydate")
	public String purchaseReportByDate() {
		return "purchasereportdate";
	}
	
	@GetMapping("/purchasereportbycategory")
	public String purchaserReportByCategory(Model model) {
		List<Category> categoryList = categoryService.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		return "purchasereportbycategory";
	}
	
	@GetMapping("/resetpassword")
	public String resetPasswordPage() {
		return "resetpassword";
	}
	
	@PostMapping("/resetpassword")
	public String resetPasswordPageSubmission(@RequestParam("username") String username, @RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword") String newpassword, @RequestParam("newconfirmpassword") String confirmnewpassword, Model model) {
		if(loginService.loginReset(username, oldpassword, newpassword, confirmnewpassword)) {
			model.addAttribute("message","reset successful");
			return "index";
		}
		else {
			model.addAttribute("message","reset unsuccessful");
			return "index";
		}
	}
	
	@PostMapping("/index")
	public String loginPage(@RequestParam("username") String username, @RequestParam("password") String password, Model model) {
		if(loginService.loginCredentialsValidator(username, password))
			return "adminpage";
		else {
			model.addAttribute("error", "Invalid Credentials");
			return "index";
		}
	}
	
	@PostMapping("/purchasereportbydate")
	public String purchaseReportByDateSubmission(@RequestParam("date") String date, Model model) {
		System.out.println(date);
		model.addAttribute("datelist", purchaseReportService.getProductReportListByDate(date));
		return "purchasereportbydateresult";
	}
	
	@PostMapping("/purchasereportbycategory")
	public String purchaseReportByCategorySubmission(@RequestParam("id") int id, Model model) {
		System.out.println(id);
		model.addAttribute("categorylist", purchaseReportService.getProductReportListByCategory(id));
		return "purchasereportbycategoryresult";
	}
	
	@PostMapping("/searchuserform")
	public String searchUserSubmission(@RequestParam("username") String username, Model model) {
		System.out.println(username);
		User user = userService.searchUser(username);
		if(user != null) {
			model.addAttribute("user", user);
			return "searchresult";
		}
		else return "searchresult";
			
	}
	
	@PostMapping("/categorysubmission")
	public String categorySubmission(@ModelAttribute("category") Category category) {
		System.out.println("post mapping reached");
		if(categoryService.saveCategory(category)) {
			return "adminpage";
		}
		return "categoryform";
	}
	
	@PostMapping("/productsubmission")
	public String productSubmission(@ModelAttribute("product") Product product) {
		if(productService.saveProduct(product)) {
			return "adminpage";
		}
		return "productform";
	}
	
	@PostMapping("/updateproduct")
	public String updateProduct(@ModelAttribute("product") Product product) {
		System.out.println(product);
		productService.updateProduct(product);
		return "adminpage";
	}

}
