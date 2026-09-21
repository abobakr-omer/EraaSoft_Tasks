package com.spring.demo.model.mapper;

import com.spring.demo.model.Course;
import com.spring.demo.model.Instructor;
import com.spring.demo.model.dto.InstructorDetailDto;
import com.spring.demo.model.dto.InstructorRequestDto;
import com.spring.demo.model.dto.InstructorResponseDto;
import com.spring.demo.model.dto.InstructorSummaryDto;
import org.springframework.stereotype.Component;

@Component
public class InstructorMapper {

    public Instructor toEntity(InstructorRequestDto instructorRequestDto) {

        Instructor instructor = new Instructor();

        instructor.setName(instructorRequestDto.getName());
        instructor.setEmail(instructorRequestDto.getEmail());

        return instructor;
    }

    public InstructorResponseDto toDto(Instructor instructor) {

        InstructorResponseDto instructorResponseDto =
                new InstructorResponseDto();

        instructorResponseDto.setId(instructor.getId());
        instructorResponseDto.setName(instructor.getName());
        instructorResponseDto.setEmail(instructor.getEmail());

        instructorResponseDto.setCourseIds(
                instructor.getCourses()
                        .stream()
                        .map(Course::getId)
                        .toList()
        );

        return instructorResponseDto;
    }

    public InstructorSummaryDto toSummaryDto(Instructor instructor) {

        return new InstructorSummaryDto(
                instructor.getId(),
                instructor.getName(),
                instructor.getEmail()
        );
    }

    public InstructorDetailDto toDetailDto(Instructor instructor,
                                           CourseMapper courseMapper,
                                           StudentMapper studentMapper) {

        InstructorDetailDto instructorDetailDto = new InstructorDetailDto();

        instructorDetailDto.setId(instructor.getId());
        instructorDetailDto.setName(instructor.getName());
        instructorDetailDto.setEmail(instructor.getEmail());
        instructorDetailDto.setCourses(
                instructor.getCourses()
                        .stream()
                        .map(course -> courseMapper.toDetailDto(course, studentMapper))
                        .toList()
        );

        return instructorDetailDto;
    }
}
