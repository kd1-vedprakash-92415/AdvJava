package com.healthcare.dtos;

import java.time.LocalDate;

public class UserDTO {
	private String firstName;
	private String lastName;
	private LocalDate dob;
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	//all args ctor
	public UserDTO(String firstName, String lastName, LocalDate dob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
	}
	//getters & setters
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	//toString
	@Override
	public String toString() {
		return "UserDTO [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + "]";
	}
	
}
