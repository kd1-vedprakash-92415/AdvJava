package com.healthcare.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.utils.HibernateUtils;

public class SaveUserImage {

	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in);
				SessionFactory sf=HibernateUtils
				.getSessionFactory())  //triggers class loading -> static init block
		{
			//create user dao instance
			UserDao userDao=new UserDaoImpl();
			System.out.println("Enter image file path");
			String path=sc.nextLine();
			System.out.println("Enter user id");
			//call dao's method
			System.out.println(userDao.saveImage(path , sc.nextLong()));
		} //JVM - sf.close() -> DBCP is cleaned up & all db resources are closed.
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
