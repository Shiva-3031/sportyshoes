package com.sportyshoesproj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoesproj.dao.UserDao;
import com.sportyshoesproj.pojo.User;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public User searchUser(String username) {
		List<User> list = getUserList();
		for(User user : list) {
			if(user.getUsername().equals(username))
				return user;
		}
		return null;
	}
	
	public List<User> getUserList(){
		return userDao.getUserList();
	}
	
	public void insertUser() {
		userDao.insertUser();
	}
}
