package com.spring.demo.service;

import com.spring.demo.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {

    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getStudentById(Long id);
}
