package com.spring.demo.controller;

import com.spring.demo.dto.post.PostRequestDto;
import com.spring.demo.dto.post.PostResponseDto;
import com.spring.demo.dto.post.PostWithUserResponseDto;
import com.spring.demo.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponseDto createPost(
            @Valid @RequestBody PostRequestDto postRequestDto
    ) {
        return postService.createPost(postRequestDto);
    }

    @GetMapping("/{id}")
    public PostResponseDto getPost(
            @PathVariable Long id
    ) {
        return postService.getPost(id);
    }

    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PutMapping("/{id}")
    public PostResponseDto updatePost(
            @PathVariable Long id,
            @Valid @RequestBody PostRequestDto postRequestDto
    ) {
        return postService.updatePost(id, postRequestDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id
    ) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/postsWithUsers")
    public List<PostWithUserResponseDto> getAllPostsWithUsers() {
        return postService.getAllPostsWithUsers();
    }

    @GetMapping("/postWithUsers/{id}")
    public PostWithUserResponseDto getPostWithUserById(
            @PathVariable Long id
    ) {
        return postService.getPostWithUserById(id);
    }
}
