package com.emps.entities;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AttributeOverride(name="id", column=@Column(name="project_id"))
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper =true)
public class Project extends BaseEntity {
	@Column(name="startDate")
	private LocalDateTime start_date;
	@Column(name="endDate")
	private LocalDateTime end_date;

	
	
}
