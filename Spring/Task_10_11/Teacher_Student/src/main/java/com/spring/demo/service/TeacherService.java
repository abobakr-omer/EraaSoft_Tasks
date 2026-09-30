package com.spring.demo.service;

import com.spring.demo.dto.TeacherResponseDto;

import java.util.List;

public interface TeacherService {

    List<TeacherResponseDto> getAllTeachers();

    TeacherResponseDto getTeacherById(Long id);
}
