package com.hibernate.demo.task3;

import javax.persistence.*;

@Entity
@Table(name = "driver")
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(mappedBy = "driver") private Car car;

	public Driver() {
	}

	public Long getId() {
		return id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
}
