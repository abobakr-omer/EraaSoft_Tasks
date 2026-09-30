package com.spring.demo.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "user.name.required")
    @Size(
            min = 8,
            message = "user.name.invalid.size"
    )
    private String name;

    @NotNull(message = "user.age.required")
    @Min(
            value = 18,
            message = "user.age.invalid"
    )
    private Integer age;

    @NotBlank(message = "user.password.required")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
            message = "user.password.invalid"
    )
    private String password;
}
