package com.spring.demo.dto.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    @NotBlank(message = "post.text.required")
    @Size(
            min = 20,
            message = "post.text.invalid.size"
    )
    private String text;

    private String imagePath;

    @NotNull(message = "post.user.id.required")
    private Long userId;
}
