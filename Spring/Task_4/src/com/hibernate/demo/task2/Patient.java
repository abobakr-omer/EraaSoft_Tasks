package com.hibernate.demo.task2;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Column(nullable = false)
	private String name;

	@Column(name = "type_of_disease", nullable = false)
	private String typeOfDisease;
	
	public List<Hospital> getHospitals() {
		return hospitals;
	}

	public void setHospitals(List<Hospital> hospitals) {
		this.hospitals = hospitals;
	}

	@ManyToOne
	@JoinColumn(name = "doctor_id" , nullable = false)
	private Doctor doctor;
	
	@ManyToMany(mappedBy = "patients")
	private List<Hospital> hospitals;
	

	public Patient() {
	}

	public Patient(String name, String typeOfDisease) {
		this.name = name;
		this.typeOfDisease = typeOfDisease;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeOfDisease() {
		return typeOfDisease;
	}

	public void setTypeOfDisease(String typeOfDisease) {
		this.typeOfDisease = typeOfDisease;
	}
	
	
	
}
