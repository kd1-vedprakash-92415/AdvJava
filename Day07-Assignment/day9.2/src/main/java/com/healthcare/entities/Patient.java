package com.healthcare.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//JPA 
@Entity
@Table(name = "patients")
@AttributeOverride(name = "id", column = @Column(name = "patient_id"))
//Lombok
@NoArgsConstructor
@Getter
@Setter
/*
 * Gavin King - Project Tip Exclude association based fields from toString - In
 * a bi directional association , it will cause recursion (StackOverflowError
 * !!!!!)
 */
@ToString(exclude = "userDetails")
public class Patient extends BaseEntity {
	@Enumerated(EnumType.STRING)
	@Column(name = "blood_group")
	private BloodGroup bloodGroup;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(name="family_history",length = 500)
	private String familyHistory;
	// Patient HAS-A User (details) Patient 1----->1 User Details
	@OneToOne //mandatory - o.w MappingException
	@JoinColumn(name="user_id",nullable = false)
	private User userDetails;
	/*
	 * Patient(s) HAS-A DiagTest(s)
        Patient m---->m DiagTest
	 */
	@ManyToMany //mandatory annotation
	/*
	 * To customize name of link table & its col names
	 */
	@JoinTable(name="patient_diag_tests"
	,joinColumns = @JoinColumn(name="patient_id")
	,inverseJoinColumns = @JoinColumn(name="test_id"))
	private Set<DiagTest> diagTests=new HashSet<>();
	
	//parameterized ctor
	public Patient(BloodGroup bloodGroup, Gender gender, String familyHistory) {
		super();
		this.bloodGroup = bloodGroup;
		this.gender = gender;
		this.familyHistory = familyHistory;
	}
	
}
