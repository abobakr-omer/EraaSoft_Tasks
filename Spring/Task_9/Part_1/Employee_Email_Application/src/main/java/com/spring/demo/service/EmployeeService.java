package com.spring.demo.service;

import com.spring.demo.dto.EmployeeRequestDto;
import com.spring.demo.dto.EmployeeResponseDto;
import com.spring.demo.dto.EmployeeWithEmailsRequestDto;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDto createEmployee(
            EmployeeRequestDto employeeRequestDto
    );


    EmployeeResponseDto createEmployeeWithEmails(
            EmployeeWithEmailsRequestDto employeeRequestDto
    );


    EmployeeResponseDto updateEmployee(
            Long id,
            EmployeeRequestDto employeeRequestDto
    );


    void deleteEmployee(Long id);


    List<EmployeeResponseDto> getAllEmployees();


    EmployeeResponseDto getEmployeeById(Long id);


    List<EmployeeResponseDto> getEmployeesByIds(
            List<Long> ids
    );


    List<EmployeeResponseDto> getEmployeesByNames(
            List<String> names
    );
}