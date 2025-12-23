package com.emps.dao;
import java.util.List;
import java.time.LocalDate;
import com.emps.entities.EmpType;
import com.emps.dto.EmployeeBasicDTO;

import com.emps.entities.Employee;
public interface EmployeeDao {
	String addNewEmpDetails(Employee emp);
	
	Employee getEmpDetails(Long empId);
	
	List<Employee> getAllEmps();

	List<EmployeeBasicDTO> getEmpByDobAndDept(LocalDate dob, String deptName);

	String updateEmpSalary(String email, int salaryIncrement);
	
	String incrementSalaryByDept(String deptName, double incrementPercent);

	String deleteEmpsByEmpType(EmpType empType);

}
