package com.spring.demo.repo;

import com.spring.demo.model.Appointment;
import com.spring.demo.model.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {


    List<Appointment> findByDoctor_IdAndStatusNot
            (Long doctorId, AppointmentStatus status);

    List<Appointment> findByPatient_IdAndStatusNot
            (Long patientId, AppointmentStatus status);


}
