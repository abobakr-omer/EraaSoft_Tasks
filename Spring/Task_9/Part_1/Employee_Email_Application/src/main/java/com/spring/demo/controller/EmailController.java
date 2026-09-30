package com.spring.demo.controller;

import com.spring.demo.dto.EmailRequestDto;
import com.spring.demo.dto.EmailResponseDto;
import com.spring.demo.service.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;


    @PostMapping
    public ResponseEntity<EmailResponseDto> createEmail(
            @Valid
            @RequestBody
            EmailRequestDto emailRequestDto
    ) {

        EmailResponseDto email =
                emailService.createEmail(
                        emailRequestDto
                );


        URI location =
                URI.create(
                        "/api/emails/" + email.getId()
                );


        return ResponseEntity
                .created(location)
                .body(email);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmailResponseDto> updateEmail(
            @PathVariable Long id,
            @Valid
            @RequestBody
            EmailRequestDto emailRequestDto
    ) {

        EmailResponseDto email =
                emailService.updateEmail(
                        id,
                        emailRequestDto
                );


        return ResponseEntity.ok(email);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmail(
            @PathVariable Long id
    ) {

        emailService.deleteEmail(id);


        return ResponseEntity
                .noContent()
                .build();
    }


    @GetMapping
    public ResponseEntity<List<EmailResponseDto>>
    getAllEmails() {

        List<EmailResponseDto> emails =
                emailService.getAllEmails();


        return ResponseEntity.ok(emails);
    }


    @GetMapping("/by-name")
    public ResponseEntity<List<EmailResponseDto>>
    getEmailsByName(
            @RequestParam String name
    ) {

        List<EmailResponseDto> emails =
                emailService.getEmailsByName(name);


        return ResponseEntity.ok(emails);
    }


    @GetMapping("/by-names")
    public ResponseEntity<List<EmailResponseDto>>
    getEmailsByNames(
            @RequestParam List<String> names
    ) {

        List<EmailResponseDto> emails =
                emailService.getEmailsByNames(names);


        return ResponseEntity.ok(emails);
    }


    @GetMapping("/by-content")
    public ResponseEntity<List<EmailResponseDto>>
    getEmailsByContent(
            @RequestParam String content
    ) {

        List<EmailResponseDto> emails =
                emailService.getEmailsByContent(content);


        return ResponseEntity.ok(emails);
    }
}