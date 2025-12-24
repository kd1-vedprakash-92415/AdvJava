package com.healthcare.tester;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.healthcare.dao.UserDao;
import com.healthcare.dao.UserDaoImpl;
import com.healthcare.entities.User;
import com.healthcare.entities.UserRole;
import com.healthcare.utils.HibernateUtils;

public class DisplayUsersLastNamesByRole {

	public static void main(String[] args) {
		try (Scanner sc=new Scanner(System.in);
				SessionFactory sf=HibernateUtils
				.getSessionFactory())  //triggers class loading -> static init block
		{
			//create user dao instance
			UserDao userDao=new UserDaoImpl();
			System.out.println("Enter user role ");
					//call dao's method
			userDao.getUserLastNameByRole(UserRole.valueOf(sc.next().toUpperCase())).forEach(System.out::println);
		} //JVM - sf.close() -> DBCP is cleaned up & all db resources are closed.
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
