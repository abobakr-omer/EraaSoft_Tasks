package com.spring.demo.controller;

import com.spring.demo.dto.post.PostResponseDto;
import com.spring.demo.dto.user.UserRequestDto;
import com.spring.demo.dto.user.UserResponseDto;
import com.spring.demo.dto.user.UserWithPostsResponseDto;
import com.spring.demo.service.UserService;
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
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDto createUser(
            @Valid @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.createUser(userRequestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUser(
            @PathVariable Long id
    ) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public UserResponseDto updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDto userRequestDto
    ) {
        return userService.updateUser(userRequestDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/posts")
    public List<PostResponseDto> getPostsByUserId(
            @PathVariable Long id
    ) {
        return userService.getPostsByUserId(id);
    }

    @GetMapping("/usersWithPost")
    public List<UserWithPostsResponseDto> getAllUsersWithPosts() {
        return userService.getAllUsersWithPosts();
    }

    @GetMapping("/userWithPost/{id}")
    public UserWithPostsResponseDto getUserWithPostsById(
            @PathVariable Long id
    ) {
        return userService.getUserWithPostsById(id);
    }
}
