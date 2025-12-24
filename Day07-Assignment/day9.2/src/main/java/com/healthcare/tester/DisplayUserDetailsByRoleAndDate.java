package com.healthcare.tester;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;
import com.healthcare.utils.HibernateUtils;

public class DisplayUserDetailsByRoleAndDate {

	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in);
				SessionFactory sf=HibernateUtils
				.getSessionFactory())  //triggers class loading -> static init block
		{
			//create user dao instance
			UserDao userDao=new UserDaoImpl();
			System.out.println("Enter user role & date(yr-mon-day)");
					//call dao's method
			userDao.getUserDetailsByRoleAndDate(UserRole.valueOf(sc.next().toUpperCase()),LocalDate.parse(sc.next())
					).forEach(System.out::println);
		} //JVM - sf.close() -> DBCP is cleaned up & all db resources are closed.
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
