package com.spring.demo.service;

import com.spring.demo.dto.EmailRequestDto;
import com.spring.demo.dto.EmailResponseDto;

import java.util.List;

public interface EmailService {

    EmailResponseDto createEmail(
            EmailRequestDto emailRequestDto
    );


    EmailResponseDto updateEmail(
            Long id,
            EmailRequestDto emailRequestDto
    );


    void deleteEmail(Long id);


    List<EmailResponseDto> getAllEmails();


    List<EmailResponseDto> getEmailsByName(
            String name
    );


    List<EmailResponseDto> getEmailsByNames(
            List<String> names
    );


    List<EmailResponseDto> getEmailsByContent(
            String content
    );
}