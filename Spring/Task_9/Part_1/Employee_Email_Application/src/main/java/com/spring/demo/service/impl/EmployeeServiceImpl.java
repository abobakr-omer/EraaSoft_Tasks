package com.spring.demo.service.impl;

import com.spring.demo.dto.EmailEmbeddedRequestDto;
import com.spring.demo.dto.EmployeeRequestDto;
import com.spring.demo.dto.EmployeeResponseDto;
import com.spring.demo.dto.EmployeeWithEmailsRequestDto;
import com.spring.demo.model.Email;
import com.spring.demo.model.Employee;
import com.spring.demo.exception.ResourceNotFoundException;
import com.spring.demo.mapper.EmailMapper;
import com.spring.demo.mapper.EmployeeMapper;
import com.spring.demo.repo.EmployeeRepository;
import com.spring.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    private final EmailMapper emailMapper;


    @Override
    public EmployeeResponseDto createEmployee(
            EmployeeRequestDto employeeRequestDto
    ) {

        Employee employee =
                employeeMapper.toEntity(employeeRequestDto);

        Employee savedEmployee =
                employeeRepository.save(employee);

        return employeeMapper.toResponseDto(savedEmployee);
    }


    @Override
    public EmployeeResponseDto createEmployeeWithEmails(
            EmployeeWithEmailsRequestDto employeeRequestDto
    ) {

        Employee employee =
                employeeMapper.toEntity(employeeRequestDto);


        for (EmailEmbeddedRequestDto emailDto :
                employeeRequestDto.getEmails()) {

            Email email =
                    emailMapper.toEntity(emailDto);

            employee.addEmail(email);
        }


        Employee savedEmployee =
                employeeRepository.save(employee);


        return employeeMapper.toResponseDto(savedEmployee);
    }


    @Override
    public EmployeeResponseDto updateEmployee(
            Long id,
            EmployeeRequestDto employeeRequestDto
    ) {

        Employee employee =
                employeeRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Employee not found with id: " + id
                                )
                        );


        employee.setName(
                employeeRequestDto.getName()
        );

        employee.setAge(
                employeeRequestDto.getAge()
        );

        employee.setSalary(
                employeeRequestDto.getSalary()
        );


        Employee updatedEmployee =
                employeeRepository.save(employee);


        return employeeMapper.toResponseDto(
                updatedEmployee
        );
    }


    @Override
    public void deleteEmployee(Long id) {

        Employee employee =
                employeeRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Employee not found with id: " + id
                                )
                        );


        employeeRepository.delete(employee);
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getAllEmployees() {

        List<Employee> employees =
                employeeRepository.findAll();


        return employeeMapper.toResponseDtoList(
                employees
        );
    }


    @Override
    @Transactional(readOnly = true)
    public EmployeeResponseDto getEmployeeById(Long id) {

        Employee employee =
                employeeRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Employee not found with id: " + id
                                )
                        );


        return employeeMapper.toResponseDto(
                employee
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getEmployeesByIds(
            List<Long> ids
    ) {

        List<Employee> employees =
                employeeRepository.findAllById(ids);


        return employeeMapper.toResponseDtoList(
                employees
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDto> getEmployeesByNames(
            List<String> names
    ) {

        List<Employee> employees =
                employeeRepository.findByNameIn(names);


        return employeeMapper.toResponseDtoList(
                employees
        );
    }
}