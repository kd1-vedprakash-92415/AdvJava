package com.emps.entities;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@AttributeOverride(name="id",column=@Column(name="address_id"))
@Table(name="Address")
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)

public class Address extends BaseEntity {
	@Column(name="street",length=50)
	private String street;
	@Column(name="city",length=50)
	private String city;
	@Column(name="state",length=50)
	private String state;
	@Column(name="zipcode")
	private int zipCode;
	
	@OneToOne
	@JoinColumn(name="employee_id",nullable=false)
	private Employee employee;
}
