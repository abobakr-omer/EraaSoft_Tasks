package com.spring.demo.dto.post;

import com.spring.demo.dto.user.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostWithUserResponseDto {

    private Long id;

    private String text;

    private String imagePath;

    private UserResponseDto user;
}
