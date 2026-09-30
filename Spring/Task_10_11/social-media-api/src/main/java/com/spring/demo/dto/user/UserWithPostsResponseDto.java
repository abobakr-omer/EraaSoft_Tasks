package com.spring.demo.dto.user;

import com.spring.demo.dto.post.PostResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithPostsResponseDto {

    private Long id;

    private String name;

    private Integer age;

    private List<PostResponseDto> posts;
}
