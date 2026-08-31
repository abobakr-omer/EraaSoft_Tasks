package com.hibernate.demo.task3;

import javax.persistence.*;

@Entity
@Table(name = "classroom")
public class Classroom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "school_id")
	private School school;

	public Classroom() {
	}

	public Long getId() {
		return id;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}
