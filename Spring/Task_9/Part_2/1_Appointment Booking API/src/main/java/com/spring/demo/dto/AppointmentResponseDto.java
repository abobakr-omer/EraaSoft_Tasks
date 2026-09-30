package com.spring.demo.dto;

import com.spring.demo.model.enums.AppointmentStatus;
import com.spring.demo.model.enums.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponseDto {

    private Long id;

    private Long doctorId;

    private String doctorName;

    private Long patientId;

    private String patientName;

    private ServiceType serviceType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private AppointmentStatus status;
}