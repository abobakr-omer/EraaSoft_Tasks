package com.spring.demo.model.mapper;

import com.spring.demo.model.Course;
import com.spring.demo.model.Student;
import com.spring.demo.model.dto.CourseDetailDto;
import com.spring.demo.model.dto.CourseRequestDto;
import com.spring.demo.model.dto.CourseResponseDto;
import com.spring.demo.model.dto.InstructorSummaryDto;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequestDto courseRequestDto){

        Course course = new Course();

        course.setCourseCode(courseRequestDto.getCourseCode());
        course.setTitle(courseRequestDto.getTitle());
        course.setDescription(courseRequestDto.getDescription());

        return course;
    }

    public CourseResponseDto toDto(Course course){

        CourseResponseDto courseResponseDto = new CourseResponseDto();

        courseResponseDto.setId(course.getId());
        courseResponseDto.setCourseCode(course.getCourseCode());
        courseResponseDto.setTitle(course.getTitle());
        courseResponseDto.setDescription(course.getDescription());

        if (course.getInstructor() != null) {
            courseResponseDto.setInstructorId(course.getInstructor().getId());
            courseResponseDto.setInstructorName(course.getInstructor().getName());
        }

        courseResponseDto.setStudentIds(
                course.getStudents()
                        .stream()
                        .map(Student::getId)
                        .toList()
        );

        return courseResponseDto;
    }

    public CourseDetailDto toDetailDto(Course course, StudentMapper studentMapper){

        CourseDetailDto courseDetailDto = new CourseDetailDto();

        courseDetailDto.setId(course.getId());
        courseDetailDto.setCourseCode(course.getCourseCode());
        courseDetailDto.setTitle(course.getTitle());
        courseDetailDto.setDescription(course.getDescription());

        if (course.getInstructor() != null) {
            courseDetailDto.setInstructor(new InstructorSummaryDto(
                    course.getInstructor().getId(),
                    course.getInstructor().getName(),
                    course.getInstructor().getEmail()
            ));
        }

        courseDetailDto.setStudents(
                course.getStudents()
                        .stream()
                        .map(studentMapper::toSummaryDto)
                        .toList()
        );

        return courseDetailDto;
    }
}
