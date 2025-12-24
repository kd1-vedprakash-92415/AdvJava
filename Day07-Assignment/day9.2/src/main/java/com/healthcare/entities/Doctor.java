package com.healthcare.entities;

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

//JPA annotations
@Entity
@Table(name="doctors")
@AttributeOverride(name="id",column = @Column(name="doctor_id"))
//Lombok annotations
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Doctor extends BaseEntity {
	@Column(name="experience_in_years")
	private int experienceInYears;
	private int fees;
	@Column(length = 100)
	private String speciality;
	private String qualifications;
	//Doctor HAS-A User (details) , Doctor 1---->1 User
	@OneToOne //mandatory
	@JoinColumn(name="user_id",nullable = false)
	private User userDetails;
}
