package com.sportyshoesproj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sportyshoesproj.pojo.Product;

public class ProductRowMapper implements RowMapper<Product> {

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductId(rs.getInt(1));
		product.setProductName(rs.getString(2));
		product.setProductCategoryId(rs.getInt(3));
		return product;
	}

}
