package com.hibernate.demo.task3;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToMany(mappedBy = "country")
	private List<City> cities;

	public Country() {
	}

	public Long getId() {
		return id;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
}
