package com.sportyshoesproj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.sportyshoesproj.pojo.User;

@Component
public class UserDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<User> getUserList(){
		String query = "select * from users where role = ?";
		System.out.println("get user list is invoked");
		return jdbcTemplate.query(query, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setUsername(String.valueOf(rs.getString(2)));
				user.setRole(rs.getString(4));
				return user;
			}
			
		},"user");
	}
	
	public void insertUser() {
		User user = new User();
		user.setRole("user");
		user.setUsername("shiva");
		user.setPassword("12345");
		
		String query = "insert into users(username, password, role) values(?,?,?)";
		jdbcTemplate.update(query, user.getUsername(), user.getPassword(), user.getRole());
		return;
	}
	
}
