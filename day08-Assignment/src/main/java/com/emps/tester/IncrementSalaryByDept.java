package com.emps.tester;

import java.util.Scanner;
import org.hibernate.SessionFactory;

import com.emps.dao.EmployeeDao;
import com.emps.dao.EmployeeDaoImpl;
import com.emps.utils.HibernateUtils;

public class IncrementSalaryByDept {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				SessionFactory sf = HibernateUtils.getSessionFactory()) {

			EmployeeDao dao = new EmployeeDaoImpl();

			System.out.println("Enter department name and salary increment percentage:");
			String dept = sc.next();
			double percent = sc.nextDouble();

			System.out.println(dao.incrementSalaryByDept(dept, percent));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
