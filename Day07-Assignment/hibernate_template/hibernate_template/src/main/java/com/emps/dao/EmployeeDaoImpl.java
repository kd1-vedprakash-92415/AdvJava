package com.emps.dao;

import com.emps.entities.Employee;
import org.hibernate.*;
import static com.emps.utils.HibernateUtils.getSessionFactory;
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

}
