package com.spring.demo.service.impl;

import com.spring.demo.exception.DuplicateResourceException;
import com.spring.demo.exception.ResourceNotFoundException;
import com.spring.demo.model.Course;
import com.spring.demo.model.Student;
import com.spring.demo.model.dto.StudentDetailDto;
import com.spring.demo.model.dto.StudentRequestDto;
import com.spring.demo.model.dto.StudentResponseDto;
import com.spring.demo.model.mapper.CourseMapper;
import com.spring.demo.model.mapper.StudentMapper;
import com.spring.demo.repo.CourseRepo;
import com.spring.demo.repo.StudentRepo;
import com.spring.demo.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;
    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo,
                              StudentMapper studentMapper,
                              CourseRepo courseRepo,
                              CourseMapper courseMapper) {
        this.studentRepo = studentRepo;
        this.studentMapper = studentMapper;
        this.courseRepo = courseRepo;
        this.courseMapper = courseMapper;
    }

    @Override
    @Transactional
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {

        if (studentRepo.existsByEmail(studentRequestDto.getEmail())) {
            throw new DuplicateResourceException(
                    "Student already exists with email: " + studentRequestDto.getEmail());
        }

        Student savedStudent = studentRepo.save(studentMapper.toEntity(studentRequestDto));

        return studentMapper.toDto(savedStudent);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponseDto> getAllStudents() {

        return studentRepo.findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponseDto getStudentById(Long studentId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + studentId));

        return studentMapper.toDto(student);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDetailDto getStudentDetail(Long studentId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + studentId));

        return studentMapper.toDetailDto(student, courseMapper);
    }

    @Override
    @Transactional
    public StudentResponseDto registerStudentInCourse(Long studentId, Long courseId) {

        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + studentId));

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with id: " + courseId));

        boolean alreadyRegistered = student.getCourses()
                .stream()
                .anyMatch(c -> c.getId().equals(courseId));

        if (alreadyRegistered) {
            throw new DuplicateResourceException(
                    "Student is already registered in this course");
        }

        student.getCourses().add(course);
        course.getStudents().add(student);

        Student savedStudent = studentRepo.save(student);

        return studentMapper.toDto(savedStudent);
    }
}
