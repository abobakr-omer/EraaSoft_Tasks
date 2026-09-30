package com.spring.demo.dto;

import com.spring.demo.model.enums.ServiceType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequestDto {

    @NotNull
    private Long doctorId;

    @NotNull
    private Long patientId;

    @NotNull
    private ServiceType serviceType;

    @NotNull
    private LocalDateTime startTime;
}
