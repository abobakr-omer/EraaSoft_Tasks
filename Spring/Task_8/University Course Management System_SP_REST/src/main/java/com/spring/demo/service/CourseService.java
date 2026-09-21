package com.spring.demo.service;

import com.spring.demo.model.dto.CourseDetailDto;
import com.spring.demo.model.dto.CourseRequestDto;
import com.spring.demo.model.dto.CourseResponseDto;

import java.util.List;

public interface CourseService {

    CourseResponseDto createCourse(CourseRequestDto courseRequestDto);

    CourseResponseDto getCourse(Long courseId);

    CourseDetailDto getCourseDetail(Long courseId);

    List<CourseResponseDto> getAllCourses();

    CourseResponseDto assignInstructorToCourse(Long instructorId, Long courseId);
}
