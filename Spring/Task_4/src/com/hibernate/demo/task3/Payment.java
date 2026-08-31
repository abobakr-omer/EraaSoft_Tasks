package com.hibernate.demo.task3;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "invoice_id", unique = true)
	private Invoice invoice;

	public Payment() {
	}

	public Long getId() {
		return id;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}
