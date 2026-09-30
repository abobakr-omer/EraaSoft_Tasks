package com.spring.demo.mapper;

import com.spring.demo.dto.EmployeeRequestDto;
import com.spring.demo.dto.EmployeeResponseDto;
import com.spring.demo.dto.EmployeeWithEmailsRequestDto;
import com.spring.demo.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = EmailMapper.class
)
public interface EmployeeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emails", ignore = true)
    Employee toEntity(EmployeeRequestDto employeeRequestDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "emails", ignore = true)
    Employee toEntity(EmployeeWithEmailsRequestDto employeeRequestDto);


    EmployeeResponseDto toResponseDto(Employee employee);


    List<EmployeeResponseDto> toResponseDtoList(
            List<Employee> employees
    );
}