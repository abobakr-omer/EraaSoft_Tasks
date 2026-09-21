package com.spring.demo.model.mapper;

import com.spring.demo.model.Course;
import com.spring.demo.model.Student;
import com.spring.demo.model.dto.StudentDetailDto;
import com.spring.demo.model.dto.StudentRequestDto;
import com.spring.demo.model.dto.StudentResponseDto;
import com.spring.demo.model.dto.StudentSummaryDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDto studentRequestDto){
        Student student = new Student();

        student.setEmail(studentRequestDto.getEmail());
        student.setName(studentRequestDto.getName());

        return student;
    }

    public StudentResponseDto toDto(Student student){

        StudentResponseDto studentResponseDto = new StudentResponseDto();

        studentResponseDto.setId(student.getId());
        studentResponseDto.setName(student.getName());
        studentResponseDto.setEmail(student.getEmail());
        studentResponseDto.setCourseIds(
                student.getCourses()
                        .stream().map(Course::getId).collect(Collectors.toList()));

        return studentResponseDto;
    }

    public StudentSummaryDto toSummaryDto(Student student){

        return new StudentSummaryDto(
                student.getId(),
                student.getName(),
                student.getEmail()
        );
    }

    public StudentDetailDto toDetailDto(Student student, CourseMapper courseMapper){

        StudentDetailDto studentDetailDto = new StudentDetailDto();

        studentDetailDto.setId(student.getId());
        studentDetailDto.setName(student.getName());
        studentDetailDto.setEmail(student.getEmail());
        studentDetailDto.setCourses(
                student.getCourses()
                        .stream()
                        .map(course -> courseMapper.toDetailDto(course, this))
                        .toList()
        );

        return studentDetailDto;
    }
}
