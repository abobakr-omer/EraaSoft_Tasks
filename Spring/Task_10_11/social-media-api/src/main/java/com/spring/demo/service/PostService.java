package com.spring.demo.service;

import com.spring.demo.dto.post.PostRequestDto;
import com.spring.demo.dto.post.PostResponseDto;
import com.spring.demo.dto.post.PostWithUserResponseDto;

import java.util.List;

public interface PostService {

    PostResponseDto createPost(PostRequestDto postRequestDto);

    PostResponseDto getPost(Long id);

    List<PostResponseDto> getAllPosts();

    PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto);

    void deletePost(Long postId);

    List<PostWithUserResponseDto> getAllPostsWithUsers();

    PostWithUserResponseDto getPostWithUserById(Long id);
}
