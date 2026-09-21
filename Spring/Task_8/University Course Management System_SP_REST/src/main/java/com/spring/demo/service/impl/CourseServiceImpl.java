package com.spring.demo.service.impl;

import com.spring.demo.exception.DuplicateResourceException;
import com.spring.demo.exception.ResourceNotFoundException;
import com.spring.demo.model.Course;
import com.spring.demo.model.Instructor;
import com.spring.demo.model.dto.CourseDetailDto;
import com.spring.demo.model.dto.CourseRequestDto;
import com.spring.demo.model.dto.CourseResponseDto;
import com.spring.demo.model.mapper.CourseMapper;
import com.spring.demo.model.mapper.StudentMapper;
import com.spring.demo.repo.CourseRepo;
import com.spring.demo.repo.InstructorRepo;
import com.spring.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;
    private final InstructorRepo instructorRepo;
    private final StudentMapper studentMapper;

    @Autowired
    public CourseServiceImpl(CourseRepo courseRepo,
                             CourseMapper courseMapper,
                             InstructorRepo instructorRepo,
                             StudentMapper studentMapper) {
        this.courseRepo = courseRepo;
        this.courseMapper = courseMapper;
        this.instructorRepo = instructorRepo;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional
    public CourseResponseDto createCourse(CourseRequestDto courseRequestDto) {

        if (courseRepo.existsByCourseCode(courseRequestDto.getCourseCode())) {
            throw new DuplicateResourceException(
                    "Course already exists with code: " + courseRequestDto.getCourseCode());
        }

        Course course = courseMapper.toEntity(courseRequestDto);

        Course savedCourse = courseRepo.save(course);

        return courseMapper.toDto(savedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponseDto getCourse(Long courseId) {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with id: " + courseId));

        return courseMapper.toDto(course);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseDetailDto getCourseDetail(Long courseId) {

        Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with id: " + courseId));

        return courseMapper.toDetailDto(course, studentMapper);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDto> getAllCourses() {

        return courseRepo.findAll()
                .stream()
                .map(courseMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public CourseResponseDto assignInstructorToCourse(Long instructorId, Long courseId) {

        Course existCourse = courseRepo.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with id: " + courseId));

        Instructor existInstructor = instructorRepo.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Instructor not found with id: " + instructorId));

        // remove from the previous instructor so both sides stay consistent
        if (existCourse.getInstructor() != null) {
            existCourse.getInstructor().getCourses().remove(existCourse);
        }

        existCourse.setInstructor(existInstructor);
        existInstructor.getCourses().add(existCourse);

        Course savedCourse = courseRepo.save(existCourse);

        return courseMapper.toDto(savedCourse);
    }
}
