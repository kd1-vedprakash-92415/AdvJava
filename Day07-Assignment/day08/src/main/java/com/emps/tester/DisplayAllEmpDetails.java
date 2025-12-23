package com.emps.tester;

import org.hibernate.SessionFactory;

import com.emps.dao.EmployeeDao;
import com.emps.dao.EmployeeDaoImpl;
import com.emps.utils.HibernateUtils;

public class DisplayAllEmpDetails {

	public static void main(String[] args) {
		try(SessionFactory sf = HibernateUtils
				.getSessionFactory()){
			EmployeeDao empDao = new EmployeeDaoImpl();
			System.out.println("All Employees: ");
			empDao.getAllEmps().forEach(System.out::println);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
