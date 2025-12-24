package com.healthcare.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//JPA annotations
@Entity
@Table(name = "diagnostic_tests")
//Lombok annotations
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(of="name",callSuper = false)
public class DiagTest extends BaseEntity {
	@Column(length =200,unique = true)
	private String name;
	private String description;
	private int price;
}
