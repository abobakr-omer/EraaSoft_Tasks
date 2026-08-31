package com.hibernate.demo.task3;

import javax.persistence.*;

@Entity
@Table(name = "child")
public class Child {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Parent parent;

	public Child() {
	}

	public Long getId() {
		return id;
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}
}
