package com.spring.demo.mapper;

import com.spring.demo.dto.StudentSimpleDto;
import com.spring.demo.dto.TeacherResponseDto;
import com.spring.demo.model.Student;
import com.spring.demo.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherResponseDto toResponseDto(Teacher teacher);

    StudentSimpleDto toSimpleDto(Student student);
}
