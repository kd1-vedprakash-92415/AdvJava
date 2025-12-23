package com.emps.tester;

import org.hibernate.SessionFactory;
import com.emps.utils.HibernateUtils;

public class TestHibernate {

	public static void main(String[] args) {
		try(SessionFactory sf=HibernateUtils.getSessionFactory()){
			System.out.println("Hibernate up & running...");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
