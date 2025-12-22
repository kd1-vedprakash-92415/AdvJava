package com.emps.entities;
import java.time.LocalDate;
import jakarta.persistence.*;
@Entity
@Table(name="employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="emp_id")
	private Long empId;
	@Column(name="name",length = 50)
	private String Name;
	@Column(name="salary")
	private int Salary;
	private LocalDate dob;
	@Enumerated(EnumType.STRING)
	@Column(name="emp_type")
	private EmpType empType;
	@Column(name="department",length = 50)
	private String deptName;
	@Lob 
	private byte[] image;
	
	public Employee(Long empId, String name, int salary, LocalDate dob, EmpType empType, String deptName,
			byte[] image) {
		super();
		this.empId = empId;
		Name = name;
		Salary = salary;
		this.dob = dob;
		this.empType = empType;
		this.deptName = deptName;
		this.image = image;
	}
	public Long getEmpId() {
		return empId;
	}
	public void setEmpId(Long empId) {
		this.empId = empId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getSalary() {
		return Salary;
	}
	public void setSalary(int salary) {
		Salary = salary;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public EmpType getEmpType() {
		return empType;
	}
	public void setEmpType(EmpType empType) {
		this.empType = empType;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "Employee [empId=" + empId+", Name=" +Name+", Salary="+Salary+", dob="+dob+"empType="+empType+"department="+deptName+"]";
	}
	}
