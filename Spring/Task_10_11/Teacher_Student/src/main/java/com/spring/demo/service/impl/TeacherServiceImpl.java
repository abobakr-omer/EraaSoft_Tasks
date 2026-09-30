package com.spring.demo.service.impl;

import com.spring.demo.dto.TeacherResponseDto;
import com.spring.demo.mapper.TeacherMapper;
import com.spring.demo.model.Teacher;
import com.spring.demo.repo.TeacherRepository;
import com.spring.demo.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Override
    public List<TeacherResponseDto> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toResponseDto)
                .toList();
    }

    @Override
    public TeacherResponseDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        return teacherMapper.toResponseDto(teacher);
    }
}
