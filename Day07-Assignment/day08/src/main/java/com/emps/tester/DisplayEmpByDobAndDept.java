package com.emps.tester;

import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.emps.dao.EmployeeDao;
import com.emps.dao.EmployeeDaoImpl;
import com.emps.dto.EmployeeBasicDTO;
import com.emps.utils.HibernateUtils;

public class DisplayEmpByDobAndDept {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				SessionFactory sf = HibernateUtils.getSessionFactory()) {

			EmployeeDao dao = new EmployeeDaoImpl();

			System.out.println("Enter date (yyyy-mm-dd) and department name:");
			LocalDate date = LocalDate.parse(sc.next());
			String dept = sc.next();

			dao.getEmpByDobAndDept(date, dept)
			   .forEach(System.out::println);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
