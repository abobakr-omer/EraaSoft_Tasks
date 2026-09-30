package com.spring.demo.mapper;

import com.spring.demo.dto.EmailEmbeddedRequestDto;
import com.spring.demo.dto.EmailRequestDto;
import com.spring.demo.dto.EmailResponseDto;
import com.spring.demo.model.Email;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    Email toEntity(EmailRequestDto emailRequestDto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    Email toEntity(EmailEmbeddedRequestDto emailRequestDto);


    @Mapping(source = "employee.id", target = "employeeId")
    EmailResponseDto toResponseDto(Email email);


    List<EmailResponseDto> toResponseDtoList(
            List<Email> emails
    );
}