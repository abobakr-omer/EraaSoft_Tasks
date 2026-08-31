package com.hibernate.demo.task3;

import javax.persistence.*;

@Entity
@Table(name = "hotel_room")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "hotel_id")
	private Hotel hotel;

	public Room() {
	}

	public Long getId() {
		return id;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
}
