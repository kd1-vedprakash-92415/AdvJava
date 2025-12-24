package com.healthcare.tester;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.utils.HibernateUtils;

public class DisplayAllUserDetails {

	public static void main(String[] args) {
		try (	SessionFactory sf=HibernateUtils
				.getSessionFactory())  //triggers class loading -> static init block
		{
			//create user dao instance
			UserDao userDao=new UserDaoImpl();
			System.out.println("All Users - ");
					//call dao's method
			userDao.getAllUsers().forEach(System.out::println);
		} //JVM - sf.close() -> DBCP is cleaned up & all db resources are closed.
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
