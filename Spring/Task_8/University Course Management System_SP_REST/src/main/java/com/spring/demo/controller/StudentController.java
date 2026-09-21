package com.spring.demo.controller;

import com.spring.demo.model.dto.StudentDetailDto;
import com.spring.demo.model.dto.StudentRequestDto;
import com.spring.demo.model.dto.StudentResponseDto;
import com.spring.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/create-student")
    public ResponseEntity<StudentResponseDto> createStudent(
            @Valid @RequestBody StudentRequestDto studentRequestDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.createStudent(studentRequestDto));
    }


    @GetMapping("/all-students")
    public List<StudentResponseDto> getAllStudents() {

        return studentService.getAllStudents();
    }


    // Returns the student info and a list of enrolled courses,
    // each with course info and instructor info.
    @GetMapping("/{id}")
    public StudentDetailDto getStudent(@PathVariable Long id) {

        return studentService.getStudentDetail(id);
    }


    @PutMapping("/{studentId}/register-course/{courseId}")
    public StudentResponseDto registerStudentInCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        return studentService.registerStudentInCourse(
                studentId,
                courseId
        );
    }
}
