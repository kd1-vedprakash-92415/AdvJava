package com.sunbeam.beans;

import java.sql.Date;

import com.sunbeam.daos.UserDao;
import com.sunbeam.daos.UserDaoImpl;
import com.sunbeam.pojos.User;

public class RegistrationBean {
	private String fname, lname, email, passwd, birth;
	private String role;
	private boolean status, regStatus;
	
	public RegistrationBean() {
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(boolean regStatus) {
		this.regStatus = regStatus;
	}
	
	public void registerUser() {
		try(UserDao userDao = new UserDaoImpl()) {
			Date dob = Date.valueOf(birth);
			User user = new User(0, fname, lname, email, passwd, dob, status, role);
			int count = userDao.save(user);
			this.regStatus = count == 1;
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
