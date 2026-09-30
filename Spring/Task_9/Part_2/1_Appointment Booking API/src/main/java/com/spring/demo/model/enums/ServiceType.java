package com.spring.demo.model.enums;

public enum ServiceType {

    CONSULTATION(30),
    FOLLOW_UP(15),
    CHECKUP(45);

    private final int durationMinutes;

    ServiceType(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }
}
