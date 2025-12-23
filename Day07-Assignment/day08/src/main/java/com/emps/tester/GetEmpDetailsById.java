package com.emps.tester;

import java.util.Scanner;

import org.hibernate.SessionFactory;
import com.emps.dao.EmployeeDao;
import com.emps.dao.EmployeeDaoImpl;
import com.emps.entities.Employee;
import com.emps.entities.EmpType;
import com.emps.utils.HibernateUtils;

public class GetEmpDetailsById {
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);
				SessionFactory sf= HibernateUtils.getSessionFactory())
		{
			EmployeeDao empDao = new EmployeeDaoImpl();
			System.out.println("Enter user id");
			System.out.println(empDao.getEmpDetails(sc.nextLong()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
