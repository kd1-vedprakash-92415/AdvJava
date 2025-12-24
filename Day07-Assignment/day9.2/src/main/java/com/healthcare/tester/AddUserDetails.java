package com.healthcare.tester;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;
import com.healthcare.utils.HibernateUtils;

public class AddUserDetails {

	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in);
				SessionFactory sf=HibernateUtils
				.getSessionFactory())  //triggers class loading -> static init block
		{
			//create user dao instance
			UserDao userDao=new UserDaoImpl();
			System.out.println("Enter user details -  firstName,  lastName,  email,  password,  dob,  regAmount, userRole,  phone");
			//create user object
			User user;//user - does not exist in heap
			user=new User(sc.next(),sc.next(),sc.next(),sc.next(),LocalDate.parse(sc.next()),sc.nextInt(),UserRole.valueOf(sc.next().toUpperCase()),sc.next());//user - TRANSIENT
			//call dao's method
			System.out.println(userDao.addNewUserDetails(user));
		} //JVM - sf.close() -> DBCP is cleaned up & all db resources are closed.
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
