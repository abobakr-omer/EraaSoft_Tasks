package com.spring.demo.service.impl;

import com.spring.demo.exception.DuplicateResourceException;
import com.spring.demo.exception.ResourceNotFoundException;
import com.spring.demo.model.Instructor;
import com.spring.demo.model.dto.CourseResponseDto;
import com.spring.demo.model.dto.InstructorDetailDto;
import com.spring.demo.model.dto.InstructorRequestDto;
import com.spring.demo.model.dto.InstructorResponseDto;
import com.spring.demo.model.mapper.CourseMapper;
import com.spring.demo.model.mapper.InstructorMapper;
import com.spring.demo.model.mapper.StudentMapper;
import com.spring.demo.repo.InstructorRepo;
import com.spring.demo.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepo instructorRepo;
    private final InstructorMapper instructorMapper;
    private final CourseMapper courseMapper;
    private final StudentMapper studentMapper;

    @Autowired
    public InstructorServiceImpl(InstructorRepo instructorRepo,
                                 InstructorMapper instructorMapper,
                                 CourseMapper courseMapper,
                                 StudentMapper studentMapper) {
        this.instructorRepo = instructorRepo;
        this.instructorMapper = instructorMapper;
        this.courseMapper = courseMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional
    public InstructorResponseDto createInstructor(
            InstructorRequestDto instructorRequestDto) {

        if (instructorRepo.existsByEmail(instructorRequestDto.getEmail())) {
            throw new DuplicateResourceException(
                    "Instructor already exists with email: " + instructorRequestDto.getEmail());
        }

        Instructor savedInstructor = instructorRepo.save(
                instructorMapper.toEntity(instructorRequestDto)
        );

        return instructorMapper.toDto(savedInstructor);
    }

    @Override
    @Transactional(readOnly = true)
    public InstructorResponseDto getInstructor(Long instructorId) {

        Instructor instructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Instructor not found with id: " + instructorId));

        return instructorMapper.toDto(instructor);
    }

    @Override
    @Transactional(readOnly = true)
    public InstructorDetailDto getInstructorDetail(Long instructorId) {

        Instructor instructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Instructor not found with id: " + instructorId));

        return instructorMapper.toDetailDto(instructor, courseMapper, studentMapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InstructorResponseDto> getAllInstructors() {

        return instructorRepo.findAll()
                .stream()
                .map(instructorMapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDto> getCoursesTaughtByInstructor(Long instructorId) {

        Instructor instructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Instructor not found with id: " + instructorId));

        return instructor.getCourses()
                .stream()
                .map(courseMapper::toDto)
                .toList();
    }
}
