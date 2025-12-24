package com.healthcare.tester;

import org.hibernate.SessionFactory;

import com.healthcare.utils.HibernateUtils;

public class TestHibernate {

	public static void main(String[] args) {
		try (SessionFactory sf=HibernateUtils
				.getSessionFactory())  //triggers class loading -> static init block -> configure -> DBCP -> SF
		{
			System.out.println("Hibernate SF up & running....");		
		} //JVM - sf.close() -> DBCP is cleaned up & all db resources are closed.
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
