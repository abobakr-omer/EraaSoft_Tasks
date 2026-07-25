package com.ewallet.model;

// Represents one row from the ACCOUNTS table.
public class Account {

    private Long id;
    private String username;
    private String password;
    private int age;
    private String phoneNumber;
    private double balance;

    // Required when an empty object is filled from a database result.
    public Account() {
    }

    // Used when a user creates a new account.
    public Account(String username, String password, int age, String phoneNumber) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.balance = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
