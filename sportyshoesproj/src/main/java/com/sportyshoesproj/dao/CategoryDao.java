package com.sportyshoesproj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sportyshoesproj.pojo.Category;

@Repository
public class CategoryDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public boolean saveCategory(Category category) {
		String insertQuery = "insert into category(categoryName) values(?)";
		jdbcTemplate.update(insertQuery,category.getCategoryName());
		return true;
	}
	
	public List<Category> getCategoryList() {
		return jdbcTemplate.query("select * from category", new RowMapper<Category>() {

			public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
				Category category = new Category();
				category.setCategoryId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));
				return category;
			}
		});
	}

}
