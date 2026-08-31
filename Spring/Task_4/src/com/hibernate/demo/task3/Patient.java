package com.hibernate.demo.task3;

import javax.persistence.*;

@Entity
@Table(name = "patient_task3")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;

	public Patient() {
	}

	public Long getId() {
		return id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}
