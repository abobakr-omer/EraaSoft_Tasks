package com.hibernate.task6.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends User {

    private String address;

    public Customer() {
    }

    public Customer(String name, int age, String address) {
        super(name, age);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
