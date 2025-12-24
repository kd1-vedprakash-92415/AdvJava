package com.emps.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Department_table")

@AttributeOverride(name="id", column=@Column(name="dept_id"))
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper=true)

public class Department extends BaseEntity {
	@Column(name="dept_name", length= 50, unique = true)
	private String Dept_Name;
	
	@Column(name="location",length=50)
	private String location;
	
	@ManyToOne
	@JoinColumn(name="employee_id",nullable=false)
	private Employee employee;
	
	
}
