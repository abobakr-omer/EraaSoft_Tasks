package com.spring.demo.controller;

import com.spring.demo.model.dto.CourseDetailDto;
import com.spring.demo.model.dto.CourseRequestDto;
import com.spring.demo.model.dto.CourseResponseDto;
import com.spring.demo.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping("/create-course")
    public ResponseEntity<CourseResponseDto> createCourse(
            @Valid @RequestBody CourseRequestDto courseRequestDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseService.createCourse(courseRequestDto));
    }


    @GetMapping("/all-courses")
    public List<CourseResponseDto> getAllCourses() {

        return courseService.getAllCourses();
    }


    // Returns the course info, instructor info, and all enrolled students.
    @GetMapping("/{id}")
    public CourseDetailDto getCourse(@PathVariable Long id) {

        return courseService.getCourseDetail(id);
    }


    @PutMapping("/{courseId}/assign-instructor/{instructorId}")
    public CourseResponseDto assignInstructorToCourse(
            @PathVariable Long courseId,
            @PathVariable Long instructorId) {

        return courseService.assignInstructorToCourse(
                instructorId,
                courseId
        );
    }
}
