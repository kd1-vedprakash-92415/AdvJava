package com.emps.entities;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="employees")
@AttributeOverride(name="id", column=@Column(name="employee_id"))

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Employee extends BaseEntity {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="emp_id")
//	private Long empId;
//	@Column(unique = true,length = 50)
	private String email;
	@Column(name="name",length = 50)
	private String Name;
	@Column(name="salary")
	private int Salary;
	private LocalDate dob;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="emp_type")
	private EmpType empType;
//	@Column(name="department",length = 50)
//	private String deptName;
	@Lob 
	private byte[] image;
//	public Employee() {
//		
//	}
	
	@ManyToMany
	@JoinTable(name="Employee_project"
	,joinColumns =@JoinColumn(name="employee_id")
	,inverseJoinColumns = @JoinColumn(name="project_id"))
	private Set<Project> project = new HashSet<>();
	
//	public Employee(String name, int salary, LocalDate dob, EmpType empType,
//			byte[] image) {
//		super();
////		this.empId = empId;
//		this.email = email;
//		Name = name;
//		Salary = salary;
//		this.dob = dob;
//		this.empType = empType;
////		this.deptName = deptName;
//		this.image = image;
//	}
//	public Long getEmpId() {
//		return empId;
//	}
//	public void setEmpId(Long empId) {
//		this.empId = empId;
//	}
//	public String getName() {
//		return Name;
//	}
//	public void setName(String name) {
//		Name = name;
//	}
//	public int getSalary() {
//		return Salary;
//	}
//	public void setSalary(int salary) {
//		Salary = salary;
//	}
//	public LocalDate getDob() {
//		return dob;
//	}
//	public void setDob(LocalDate dob) {
//		this.dob = dob;
//	}
//	public EmpType getEmpType() {
//		return empType;
//	}
//	public void setEmpType(EmpType empType) {
//		this.empType = empType;
//	}
//	public String getDeptName() {
//		return deptName;
//	}
//	public void setDeptName(String deptName) {
//		this.deptName = deptName;
//	}
//	public byte[] getImage() {
//		return image;
//	}
//	public void setImage(byte[] image) {
//		this.image = image;
//	}
//	
//	@Override
//	public String toString() {
//		return "Employee ["+"Name=" +Name+", Salary="+Salary+", dob="+dob+"empType="+empType+"]";
//	}
	}
