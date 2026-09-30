package com.spring.demo.service;

import com.spring.demo.dto.post.PostResponseDto;
import com.spring.demo.dto.user.UserRequestDto;
import com.spring.demo.dto.user.UserResponseDto;
import com.spring.demo.dto.user.UserWithPostsResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUser(Long id);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(UserRequestDto userRequestDto, Long id);

    void deleteUser(Long id);

    List<PostResponseDto> getPostsByUserId(Long id);

    List<UserWithPostsResponseDto> getAllUsersWithPosts();

    UserWithPostsResponseDto getUserWithPostsById(Long id);
}
