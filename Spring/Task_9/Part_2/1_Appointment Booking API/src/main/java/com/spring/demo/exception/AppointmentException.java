package com.spring.demo.exception;

public class AppointmentException extends RuntimeException {
    public AppointmentException(String message) {
        super(message);
    }
}
