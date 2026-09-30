package com.spring.demo.service.impl;

import com.spring.demo.exception.AppointmentException;
import com.spring.demo.exception.ResourceNotFoundException;
import com.spring.demo.model.Patient;
import com.spring.demo.repo.PatientRepository;
import com.spring.demo.service.PatientService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient createPatient(Patient patient) {

        if (patientRepository.existsByEmail(patient.getEmail())) {
            throw new AppointmentException(
                    "Patient email already exists"
            );
        }

        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(Long id) {

        return patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient not found with id: " + id
                        )
                );
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
}
