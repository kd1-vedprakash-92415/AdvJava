package com.emps.dto;



	public class EmployeeBasicDTO {
		private Long empId;
		private String name;
		private int salary;

		public EmployeeBasicDTO(Long empId, String name, int salary) {
			this.empId = empId;
			this.name = name;
			this.salary = salary;
		}

		@Override
		public String toString() {
			return "EmployeeDTO [ID=" + empId + ", Name=" + name + ", Salary=" + salary + "]";
		}
	}


