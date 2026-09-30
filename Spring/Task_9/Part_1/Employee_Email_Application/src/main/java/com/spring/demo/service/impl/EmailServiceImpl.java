package com.spring.demo.service.impl;

import com.spring.demo.dto.EmailRequestDto;
import com.spring.demo.dto.EmailResponseDto;
import com.spring.demo.model.Email;
import com.spring.demo.model.Employee;
import com.spring.demo.exception.ResourceNotFoundException;
import com.spring.demo.mapper.EmailMapper;
import com.spring.demo.repo.EmailRepository;
import com.spring.demo.repo.EmployeeRepository;
import com.spring.demo.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;

    private final EmployeeRepository employeeRepository;

    private final EmailMapper emailMapper;


    @Override
    public EmailResponseDto createEmail(
            EmailRequestDto emailRequestDto
    ) {

        Employee employee =
                employeeRepository
                        .findById(
                                emailRequestDto.getEmployeeId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Employee not found with id: "
                                                + emailRequestDto.getEmployeeId()
                                )
                        );


        Email email =
                emailMapper.toEntity(emailRequestDto);


        email.setEmployee(employee);


        Email savedEmail =
                emailRepository.save(email);


        return emailMapper.toResponseDto(
                savedEmail
        );
    }


    @Override
    public EmailResponseDto updateEmail(
            Long id,
            EmailRequestDto emailRequestDto
    ) {

        Email email =
                emailRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Email not found with id: " + id
                                )
                        );


        Employee employee =
                employeeRepository
                        .findById(
                                emailRequestDto.getEmployeeId()
                        )
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Employee not found with id: "
                                                + emailRequestDto.getEmployeeId()
                                )
                        );


        email.setName(
                emailRequestDto.getName()
        );

        email.setContent(
                emailRequestDto.getContent()
        );

        email.setEmployee(employee);


        Email updatedEmail =
                emailRepository.save(email);


        return emailMapper.toResponseDto(
                updatedEmail
        );
    }


    @Override
    public void deleteEmail(Long id) {

        Email email =
                emailRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Email not found with id: " + id
                                )
                        );


        emailRepository.delete(email);
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmailResponseDto> getAllEmails() {

        List<Email> emails =
                emailRepository.findAll();


        return emailMapper.toResponseDtoList(
                emails
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmailResponseDto> getEmailsByName(
            String name
    ) {

        List<Email> emails =
                emailRepository.findByName(name);


        return emailMapper.toResponseDtoList(
                emails
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmailResponseDto> getEmailsByNames(
            List<String> names
    ) {

        List<Email> emails =
                emailRepository.findByNameIn(names);


        return emailMapper.toResponseDtoList(
                emails
        );
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmailResponseDto> getEmailsByContent(
            String content
    ) {

        List<Email> emails =
                emailRepository.findByContent(content);


        return emailMapper.toResponseDtoList(
                emails
        );
    }
}