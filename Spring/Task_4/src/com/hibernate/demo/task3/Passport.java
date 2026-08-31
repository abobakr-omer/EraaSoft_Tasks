package com.hibernate.demo.task3;

import javax.persistence.*;

@Entity
@Table(name = "passport")
public class Passport {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "passport")
	private Student student;

	public Passport() {
	}

	public Long getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
