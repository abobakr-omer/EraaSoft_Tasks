package com.spring.demo.service;

import com.spring.demo.model.Doctor;

import java.util.List;

public interface DoctorService {
    Doctor createDoctor(Doctor doctor);

    Doctor getDoctor(Long id);

    List<Doctor> getAllDoctors();
}
