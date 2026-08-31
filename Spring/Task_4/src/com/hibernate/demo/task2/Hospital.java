package com.hibernate.demo.task2;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "hospital")
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(name = "number_of_doctors")
	private int numberOfDoctors;

	@Column(name = "number_of_patients")
	private int numberOfPatient;

	@OneToMany
	private List<Doctor> doctors;
	
	@ManyToMany
	@JoinTable(
			name = "Hospita_Patient",
			joinColumns = @JoinColumn(name = "Hospital_id"),
			inverseJoinColumns = @JoinColumn(name = "Patient_id")
			)
	private List<Patient> patients;
	
	
	
	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<Doctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public Hospital() {
	}

	public Hospital(String name, int numberOfDoctors, int numberOfPatient) {
		this.name = name;
		this.numberOfDoctors = numberOfDoctors;
		this.numberOfPatient = numberOfPatient;
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

	public int getNumberOfDoctors() {
		return numberOfDoctors;
	}

	public void setNumberOfDoctors(int numberOfDoctors) {
		this.numberOfDoctors = numberOfDoctors;
	}

	public int getNumberOfPatient() {
		return numberOfPatient;
	}

	public void setNumberOfPatient(int numberOfPatient) {
		this.numberOfPatient = numberOfPatient;
	}

}
