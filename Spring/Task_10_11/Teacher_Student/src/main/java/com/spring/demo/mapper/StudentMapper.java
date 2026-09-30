package com.spring.demo.mapper;

import com.spring.demo.dto.StudentResponseDto;
import com.spring.demo.dto.TeacherSimpleDto;
import com.spring.demo.model.Student;
import com.spring.demo.model.Teacher;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentResponseDto toResponseDto(Student student);

    TeacherSimpleDto toSimpleDto(Teacher teacher);
}
