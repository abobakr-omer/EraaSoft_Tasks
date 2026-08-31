package com.hibernate.demo.task3;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "flight_id")
	private Flight flight;

	public Ticket() {
	}

	public Long getId() {
		return id;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
