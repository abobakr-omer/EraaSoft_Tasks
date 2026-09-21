package com.spring.demo.service;

import com.spring.demo.model.dto.CourseResponseDto;
import com.spring.demo.model.dto.InstructorDetailDto;
import com.spring.demo.model.dto.InstructorRequestDto;
import com.spring.demo.model.dto.InstructorResponseDto;

import java.util.List;

public interface InstructorService {

    InstructorResponseDto createInstructor(InstructorRequestDto instructorRequestDto);

    InstructorResponseDto getInstructor(Long instructorId);

    InstructorDetailDto getInstructorDetail(Long instructorId);

    List<InstructorResponseDto> getAllInstructors();

    List<CourseResponseDto> getCoursesTaughtByInstructor(Long instructorId);
}
