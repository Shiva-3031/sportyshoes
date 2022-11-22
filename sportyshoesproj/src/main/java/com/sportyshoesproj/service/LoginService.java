package com.sportyshoesproj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoesproj.dao.LoginDao;

@Service
public class LoginService {
	
	@Autowired
	LoginDao loginDao;
	
	public boolean loginCredentialsValidator(String username, String password) {
		return loginDao.loginCredentialsValidator(username, password) != null ? true : false; 
	}
	
	public boolean loginReset(String username, String oldpassword, String newpassword, String confirmnewpassword) {
		if(newpassword.equals(confirmnewpassword)) {
			return loginDao.resetLoginPassword(username, oldpassword, newpassword);
		}
		else return false;
	}
}
