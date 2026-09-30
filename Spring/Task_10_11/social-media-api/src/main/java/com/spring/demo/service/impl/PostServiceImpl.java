package com.spring.demo.service.impl;

import com.spring.demo.dto.post.PostRequestDto;
import com.spring.demo.dto.post.PostResponseDto;
import com.spring.demo.dto.post.PostWithUserResponseDto;
import com.spring.demo.exception.ResourceNotFoundException;
import com.spring.demo.mapper.PostMapper;
import com.spring.demo.model.Post;
import com.spring.demo.model.User;
import com.spring.demo.repo.PostRepo;
import com.spring.demo.repo.UserRepo;
import com.spring.demo.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    private final PostMapper postMapper;

    private final UserRepo userRepo;

    public PostServiceImpl(
            PostRepo postRepo,
            PostMapper postMapper,
            UserRepo userRepo
    ) {
        this.postRepo = postRepo;
        this.postMapper = postMapper;
        this.userRepo = userRepo;
    }

    @Override
    @Transactional
    public PostResponseDto createPost(
            PostRequestDto postRequestDto
    ) {
        User user = userRepo.findById(postRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user.not.found.with.id",
                        postRequestDto.getUserId()
                ));

        Post post = postMapper.toEntity(postRequestDto);
        post.setUser(user);

        Post savedPost = postRepo.save(post);
        return postMapper.toDto(savedPost);
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "post.not.found.with.id",
                        id
                ));

        return postMapper.toDto(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        return postMapper.toDtoList(postRepo.findAll());
    }

    @Override
    @Transactional
    public PostResponseDto updatePost(
            Long postId,
            PostRequestDto postRequestDto
    ) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "post.not.found.with.id",
                        postId
                ));

        User user = userRepo.findById(postRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user.not.found.with.id",
                        postRequestDto.getUserId()
                ));

        postMapper.updateEntity(postRequestDto, post);
        post.setUser(user);

        Post updatedPost = postRepo.save(post);
        return postMapper.toDto(updatedPost);
    }

    @Override
    @Transactional
    public void deletePost(Long postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "post.not.found.with.id",
                        postId
                ));

        postRepo.delete(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostWithUserResponseDto> getAllPostsWithUsers() {
        return postMapper.toPostWithUserDtoList(postRepo.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public PostWithUserResponseDto getPostWithUserById(Long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "post.not.found.with.id",
                        id
                ));

        return postMapper.toPostWithUserDto(post);
    }
}
