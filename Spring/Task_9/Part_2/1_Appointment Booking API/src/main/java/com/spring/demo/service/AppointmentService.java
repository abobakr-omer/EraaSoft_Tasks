package com.spring.demo.service;

import com.spring.demo.dto.AppointmentRequestDto;
import com.spring.demo.dto.AppointmentResponseDto;

import java.util.List;

public interface AppointmentService {

    AppointmentResponseDto bookAppointment(AppointmentRequestDto requestDto);

    AppointmentResponseDto getAppointment(Long id);

    List<AppointmentResponseDto> getAllAppointments();

    AppointmentResponseDto cancelAppointment(Long id);

    AppointmentResponseDto completeAppointment(Long id);

}
