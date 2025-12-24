package com.healthcare.tester;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.UserRole;
import com.healthcare.utils.HibernateUtils;

public class ApplyDiscount {

	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in);
				SessionFactory sf=HibernateUtils
				.getSessionFactory())  //triggers class loading -> static init block
		{
			//create user dao instance
			UserDao userDao=new UserDaoImpl();
			System.out.println("Enter date ,  discount amount , role");
					//call dao's method
			System.out.println(userDao.applyDiscount(LocalDate.parse(sc.next()),sc.nextInt(),UserRole.valueOf(sc.next().toUpperCase())));
		} //JVM - sf.close() -> DBCP is cleaned up & all db resources are closed.
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
