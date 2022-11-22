package com.sportyshoesproj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sportyshoesproj.pojo.Category;
import com.sportyshoesproj.pojo.Product;

@Component
public class ProductDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean saveProduct(Product product) {
		String insertQuery = "insert into product(productName, categoryId) values(?,?)";
		jdbcTemplate.update(insertQuery,product.getProductName(),product.getProductCategoryId());
		return true;
	}
	
	public List<Product> getProductList(){
		return jdbcTemplate.query("select * from product", new RowMapper<Product>() {

			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setProductCategoryId(rs.getInt(3));
				return product;
			}
		});
	}
	
	public boolean deleteProduct(int productId) {
		String query  = "delete from product where productId = ?";
		jdbcTemplate.update(query,productId);
		return true;
	}
	
	public Optional<Product> getProductById(int id) {
		String query = "select * from product where productId = ?";
		return jdbcTemplate.query(query, new ProductRowMapper(), id).stream().findFirst();
	}
	
	public boolean updateProduct(Product product) {
		String query = "UPDATE product SET productName = ?, categoryId = ? WHERE productId = ?";
		jdbcTemplate.update(query,product.getProductName(),product.getProductCategoryId(),product.getProductId());
		return true;
	}

}
