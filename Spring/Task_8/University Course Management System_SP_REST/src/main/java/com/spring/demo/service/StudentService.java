package com.spring.demo.service;

import com.spring.demo.model.dto.StudentDetailDto;
import com.spring.demo.model.dto.StudentRequestDto;
import com.spring.demo.model.dto.StudentResponseDto;

import java.util.List;

public interface StudentService {

    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);

    List<StudentResponseDto> getAllStudents();

    StudentResponseDto getStudentById(Long studentId);

    StudentDetailDto getStudentDetail(Long studentId);

    StudentResponseDto registerStudentInCourse(Long studentId, Long courseId);
}
