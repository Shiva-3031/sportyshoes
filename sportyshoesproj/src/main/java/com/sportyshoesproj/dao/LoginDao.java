package com.sportyshoesproj.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mysql.cj.exceptions.RSAException;
import com.sportyshoesproj.pojo.User;

@Repository
public class LoginDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public User loginCredentialsValidator(String username, String password) {
		User user = null;
		try {
			user = jdbcTemplate.query("select * from users where username = ? and password = ? and role = ?", new RowMapper<User>() {
	
				@Override
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setUserId(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setRole(rs.getString(4));
					return user;
				}
				
			},username, password, "admin").stream().findFirst().get();
			
		}
		catch(Exception e) {
			return null;
		}
		return user;
	}
	
	public User getUserId(String username, String password) {
		String query = "select * from users where username = ? and password = ? and role = ?";
		return jdbcTemplate.query(query, new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUserId(rs.getInt(1));
				return user;
			}
			
		},username,password,"admin").stream().findFirst().get();
	}
	
	public boolean resetLoginPassword(String username, String oldpassword, String newpassword) {
		String query = "update users set password = ? where userid = ? ";
		int userId = getUserId(username, oldpassword).getUserId();
		System.out.println(userId);
		jdbcTemplate.update(query, newpassword, userId);
		return true;
	}
}
