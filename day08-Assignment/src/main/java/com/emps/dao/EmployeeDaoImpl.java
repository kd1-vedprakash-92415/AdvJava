package com.emps.dao;
import com.emps.dto.EmployeeBasicDTO;

import com.emps.entities.EmpType;
import com.emps.entities.Employee;
import org.hibernate.*;
import static com.emps.utils.HibernateUtils.getSessionFactory;

import java.time.LocalDate;
import java.util.List;
public class EmployeeDaoImpl implements EmployeeDao {
	@Override
	public String addNewEmpDetails(Employee emp) {
		String msg = "Adding employee details failed!!!";
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			session.persist(emp);
			tx.commit();
			msg="Added user details Successfully with ID"+emp.getEmpId();
		}catch(RuntimeException e) {
			if(tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return msg;
	}
	@Override
	public Employee getEmpDetails(Long empId) {
		Employee emp = null;
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			emp = session.find(Employee.class, empId);
			tx.commit();
		}catch(RuntimeException e) {
			if(tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return emp;
	
	}
	@Override
	public List<Employee> getAllEmps() {
		List<Employee> emps = null;
		String jpql = "select e from employees e";
		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		try {
			emps = session.createQuery(jpql,Employee.class)
					.getResultList();
			tx.commit();		
		}catch(RuntimeException e) {
			if(tx != null ) {
				tx.rollback();
			}
			throw e;
		}
		return emps;
	}

	@Override
	public List<EmployeeBasicDTO> getEmpByDobAndDept(LocalDate dob, String deptName) {
		List<EmployeeBasicDTO> list = null;

		String jpql =
		"select new com.emps.dto.EmployeeBasicDTO(e.empId, e.Name, e.Salary) " +
		"from Employee e " +
		"where e.dob < :date and e.deptName = :dept";

		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		try {
			list = session.createQuery(jpql, EmployeeBasicDTO.class)
					.setParameter("date", dob)
					.setParameter("dept", deptName)
					.getResultList();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
		return list;
	}
	@Override
	public String updateEmpSalary(String email, int salaryIncrement) {
		String msg = "Salary update failed!";

		String jpql = "select e from Employee e where e.email = :mail";

		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		try {
			Employee emp = session.createQuery(jpql, Employee.class)
					.setParameter("mail", email)
					.getSingleResult();

			// emp is now in persistent state
			emp.setSalary(emp.getSalary() + salaryIncrement);

			// no explicit update() required
			tx.commit();

			msg = "Salary updated successfully for Employee ID : " + emp.getEmpId();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return msg;
	}
	@Override
	public String incrementSalaryByDept(String deptName, double incrementPercent) {
		String msg = "Salary increment failed!";

		String jpql =
		"update Employee e set e.Salary = e.Salary + (e.Salary * :inc / 100) " +
		"where e.deptName = :dept";

		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		try {
			int updatedCount = session.createQuery(jpql)
					.setParameter("inc", incrementPercent)
					.setParameter("dept", deptName)
					.executeUpdate();

			tx.commit();

			msg = updatedCount + " employee(s) salary updated successfully.";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return msg;
	}
	@Override
	public String deleteEmpsByEmpType(EmpType empType) {
		String msg = "Employee deletion failed!";

		String jpql = "delete from Employee e where e.empType = :type";

		Session session = getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		try {
			int deletedCount = session.createQuery(jpql)
					.setParameter("type", empType)
					.executeUpdate();

			tx.commit();

			msg = deletedCount + " employee(s) deleted successfully.";
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}

		return msg;
	}



}
