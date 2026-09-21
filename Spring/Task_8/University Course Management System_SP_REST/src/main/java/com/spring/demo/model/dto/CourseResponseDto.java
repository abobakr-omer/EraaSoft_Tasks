package com.spring.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDto {

    private Long id;

    private String courseCode;

    private String title;

    private String description;

    private Long instructorId;

    private String instructorName;

    private List<Long> studentIds;
}
