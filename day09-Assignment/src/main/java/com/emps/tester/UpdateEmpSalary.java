package com.emps.tester;

import java.util.Scanner;
import org.hibernate.SessionFactory;

import com.emps.dao.EmployeeDao;
import com.emps.dao.EmployeeDaoImpl;
import com.emps.utils.HibernateUtils;

public class UpdateEmpSalary {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				SessionFactory sf = HibernateUtils.getSessionFactory()) {

			EmployeeDao dao = new EmployeeDaoImpl();

			System.out.println("Enter employee email and salary increment:");
			String email = sc.next();
			int increment = sc.nextInt();

			System.out.println(dao.updateEmpSalary(email, increment));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
