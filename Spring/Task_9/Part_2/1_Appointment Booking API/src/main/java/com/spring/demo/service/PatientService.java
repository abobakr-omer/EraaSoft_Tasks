package com.spring.demo.service;

import com.spring.demo.model.Patient;

import java.util.List;

public interface PatientService {

    Patient createPatient(Patient patient);

    Patient getPatient(Long id);

    List<Patient> getAllPatients();
}
