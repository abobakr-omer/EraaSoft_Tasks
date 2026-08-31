package com.hibernate.demo.task3;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "school")
public class School {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "school")
	private List<Classroom> classrooms;

	public School() {
	}

	public Long getId() {
		return id;
	}

	public List<Classroom> getClassrooms() {
		return classrooms;
	}

	public void setClassrooms(List<Classroom> classrooms) {
		this.classrooms = classrooms;
	}
}
