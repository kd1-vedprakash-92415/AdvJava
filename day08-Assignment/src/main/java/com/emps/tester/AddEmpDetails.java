package com.emps.tester;
import java.time.LocalDate;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import com.emps.dao.EmployeeDao;
import com.emps.dao.EmployeeDaoImpl;
import com.emps.entities.Employee;
import com.emps.entities.EmpType;
import com.emps.utils.HibernateUtils;


public class AddEmpDetails {
	public static void main(String args[]) {
		try(Scanner sc = new Scanner(System.in);
				SessionFactory sf = HibernateUtils.getSessionFactory()){
			EmployeeDao empDao = new EmployeeDaoImpl();
			System.out.println("Enter user details - Name, Salary, dob, emptype, department");
			Employee emp = new Employee(null, sc.next(),null, sc.nextInt(),LocalDate.parse(sc.next()),EmpType.valueOf(sc.next().toUpperCase()),sc.next(), null);
			System.out.println(empDao.addNewEmpDetails(emp));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
				
	}

}
