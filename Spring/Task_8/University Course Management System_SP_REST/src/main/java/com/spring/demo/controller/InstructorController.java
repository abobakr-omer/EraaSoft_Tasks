package com.spring.demo.controller;

import com.spring.demo.model.dto.CourseResponseDto;
import com.spring.demo.model.dto.InstructorDetailDto;
import com.spring.demo.model.dto.InstructorRequestDto;
import com.spring.demo.model.dto.InstructorResponseDto;
import com.spring.demo.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    @PostMapping("/create-instructor")
    public ResponseEntity<InstructorResponseDto> createInstructor(
            @Valid @RequestBody InstructorRequestDto instructorRequestDto) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(instructorService.createInstructor(instructorRequestDto));
    }


    @GetMapping("/all-instructors")
    public List<InstructorResponseDto> getAllInstructors() {

        return instructorService.getAllInstructors();
    }


    // Returns the instructor info, each course they teach,
    // and the enrolled students in each course.
    @GetMapping("/{id}")
    public InstructorDetailDto getInstructor(@PathVariable Long id) {

        return instructorService.getInstructorDetail(id);
    }


    @GetMapping("/{instructorId}/courses-taught")
    public List<CourseResponseDto> getCoursesTaughtByInstructor(
            @PathVariable Long instructorId) {

        return instructorService.getCoursesTaughtByInstructor(instructorId);
    }
}
