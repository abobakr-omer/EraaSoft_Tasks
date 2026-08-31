package com.hibernate.demo.task3;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "driver_id", unique = true)
	private Driver driver;

	public Car() {
	}

	public Long getId() {
		return id;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
}
