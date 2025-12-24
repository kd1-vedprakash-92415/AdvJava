package com.healthcare.utils;
/*
 * TO provide singleton & immutable SessionFactory(SF)
 */
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
public class HibernateUtils {
	private static SessionFactory sessionFactory;
	static {
		System.out.println("in static init block");
		sessionFactory=new Configuration() //empty config object
								.configure() //loaded config with props from hibernate.cfg.xml - creates DBCP 
								.buildSessionFactory();
	}
	//getter
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	

}
