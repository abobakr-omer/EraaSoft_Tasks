package com.spring.demo.controller;

import com.spring.demo.dto.EmployeeRequestDto;
import com.spring.demo.dto.EmployeeResponseDto;
import com.spring.demo.dto.EmployeeWithEmailsRequestDto;
import com.spring.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @PostMapping
    public ResponseEntity<EmployeeResponseDto> createEmployee(
            @Valid
            @RequestBody
            EmployeeRequestDto employeeRequestDto
    ) {

        EmployeeResponseDto employee =
                employeeService.createEmployee(
                        employeeRequestDto
                );


        URI location =
                URI.create(
                        "/api/employees/" + employee.getId()
                );


        return ResponseEntity
                .created(location)
                .body(employee);
    }


    @PostMapping("/with-emails")
    public ResponseEntity<EmployeeResponseDto>
    createEmployeeWithEmails(
            @Valid
            @RequestBody
            EmployeeWithEmailsRequestDto employeeRequestDto
    ) {

        EmployeeResponseDto employee =
                employeeService.createEmployeeWithEmails(
                        employeeRequestDto
                );


        URI location =
                URI.create(
                        "/api/employees/" + employee.getId()
                );


        return ResponseEntity
                .created(location)
                .body(employee);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(
            @PathVariable Long id,
            @Valid
            @RequestBody
            EmployeeRequestDto employeeRequestDto
    ) {

        EmployeeResponseDto employee =
                employeeService.updateEmployee(
                        id,
                        employeeRequestDto
                );


        return ResponseEntity.ok(employee);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id
    ) {

        employeeService.deleteEmployee(id);


        return ResponseEntity
                .noContent()
                .build();
    }


    @GetMapping
    public ResponseEntity<List<EmployeeResponseDto>>
    getAllEmployees() {

        List<EmployeeResponseDto> employees =
                employeeService.getAllEmployees();


        return ResponseEntity.ok(employees);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(
            @PathVariable Long id
    ) {

        EmployeeResponseDto employee =
                employeeService.getEmployeeById(id);


        return ResponseEntity.ok(employee);
    }


    @GetMapping("/by-ids")
    public ResponseEntity<List<EmployeeResponseDto>>
    getEmployeesByIds(
            @RequestParam List<Long> ids
    ) {

        List<EmployeeResponseDto> employees =
                employeeService.getEmployeesByIds(ids);


        return ResponseEntity.ok(employees);
    }


    @GetMapping("/by-names")
    public ResponseEntity<List<EmployeeResponseDto>>
    getEmployeesByNames(
            @RequestParam List<String> names
    ) {

        List<EmployeeResponseDto> employees =
                employeeService.getEmployeesByNames(names);


        return ResponseEntity.ok(employees);
    }
}