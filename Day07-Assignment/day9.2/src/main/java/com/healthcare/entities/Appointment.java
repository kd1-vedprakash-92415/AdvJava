package com.healthcare.entities;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//JPA annotations
@Entity
//add unique constraint on - doctor_id , appointment_date_time
@Table(name = "appointments",
uniqueConstraints = @UniqueConstraint(columnNames = {"appointment_date_time","doctor_id"}))
@AttributeOverride(name = "id", column = @Column(name = "appointment_id"))
//Lombok annotations
@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
public class Appointment extends BaseEntity {
	@Column(name="appointment_date_time")
	private LocalDateTime appointmentDateTime;
	@Enumerated(EnumType.STRING)
	private Status status;
	// Appointment(s) *----->1 Doctor
	@ManyToOne
	@JoinColumn(name="doctor_id",nullable = false)
	private Doctor myDoctor;
	// Appointment(s) *----->1 Patient
	@ManyToOne
	@JoinColumn(name="patient_id",nullable = false)
	private Patient myPatient;
}
