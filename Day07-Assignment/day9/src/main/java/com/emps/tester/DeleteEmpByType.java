package com.emps.tester;

import java.util.Scanner;
import org.hibernate.SessionFactory;

import com.emps.dao.EmployeeDao;
import com.emps.dao.EmployeeDaoImpl;
import com.emps.entities.EmpType;
import com.emps.utils.HibernateUtils;

public class DeleteEmpByType {
	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in);
				SessionFactory sf = HibernateUtils.getSessionFactory()) {

			EmployeeDao dao = new EmployeeDaoImpl();

			System.out.println("Enter employment type (FULL_TIME / PART_TIME / CONTRACT):");
			EmpType type = EmpType.valueOf(sc.next().toUpperCase());

			System.out.println(dao.deleteEmpsByEmpType(type));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
