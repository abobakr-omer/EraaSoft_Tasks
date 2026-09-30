package com.spring.demo.service.impl;

import com.spring.demo.dto.post.PostResponseDto;
import com.spring.demo.dto.user.UserRequestDto;
import com.spring.demo.dto.user.UserResponseDto;
import com.spring.demo.dto.user.UserWithPostsResponseDto;
import com.spring.demo.exception.ResourceNotFoundException;
import com.spring.demo.mapper.PostMapper;
import com.spring.demo.mapper.UserMapper;
import com.spring.demo.model.User;
import com.spring.demo.repo.UserRepo;
import com.spring.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    private final PostMapper postMapper;

    public UserServiceImpl(
            UserRepo userRepo,
            UserMapper userMapper,
            PostMapper postMapper
    ) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.postMapper = postMapper;
    }

    @Override
    @Transactional
    public UserResponseDto createUser(
            UserRequestDto userRequestDto
    ) {
        User user = userMapper.toEntity(userRequestDto);
        User savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user.not.found.with.id",
                        id
                ));

        return userMapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userMapper.toDtoList(userRepo.findAll());
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(
            UserRequestDto userRequestDto,
            Long id
    ) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user.not.found.with.id",
                        id
                ));

        userMapper.updateEntity(userRequestDto, user);

        User updatedUser = userRepo.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user.not.found.with.id",
                        id
                ));

        userRepo.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> getPostsByUserId(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user.not.found.with.id",
                        id
                ));

        return postMapper.toDtoList(user.getPosts());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserWithPostsResponseDto> getAllUsersWithPosts() {
        return userMapper.toUserWithPostsDtoList(userRepo.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public UserWithPostsResponseDto getUserWithPostsById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user.not.found.with.id",
                        id
                ));

        return userMapper.toUserWithPostsDto(user);
    }
}
