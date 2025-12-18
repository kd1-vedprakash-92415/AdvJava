package com.sunbeam.beans;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

public class LoginBean {
	String email;
	String password;
	User user =null;
	
	public LoginBean() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginBean(String email, String password, User user) {
		super();
		this.email = email;
		this.password = password;
		this.user = user;
	}
	public void authenticate() {
		try(UserDao userDao = new UserDaoImpl()){
			User users = userDao.findByEmail(email);
			if(users!=null && users.getPasswd().equals(password)) {
				setUser(users);
			}
			else {
				System.out.println("User Not Found");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
