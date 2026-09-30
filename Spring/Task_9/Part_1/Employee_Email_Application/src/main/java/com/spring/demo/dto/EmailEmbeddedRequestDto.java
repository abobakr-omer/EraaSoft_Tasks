package com.spring.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailEmbeddedRequestDto {

    @NotBlank(message = "Email name must not be null or empty")
    private String name;

    @NotBlank(message = "Email content must not be null or empty")
    @Email(
            regexp = "^[\\w.+-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-zA-Z]{2,}$",
            message = "Email content must be a valid email address"
    )
    private String content;
}