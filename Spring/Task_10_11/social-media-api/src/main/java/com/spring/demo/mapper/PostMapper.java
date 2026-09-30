package com.spring.demo.mapper;

import com.spring.demo.dto.post.PostRequestDto;
import com.spring.demo.dto.post.PostResponseDto;
import com.spring.demo.dto.post.PostWithUserResponseDto;
import com.spring.demo.dto.user.UserResponseDto;
import com.spring.demo.model.Post;
import com.spring.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostResponseDto toDto(Post post);

    List<PostResponseDto> toDtoList(List<Post> posts);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    Post toEntity(PostRequestDto postRequestDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    void updateEntity(
            PostRequestDto postRequestDto,
            @MappingTarget Post post
    );

    @Mapping(target = "user", qualifiedByName = "toUserDto")
    PostWithUserResponseDto toPostWithUserDto(Post post);

    List<PostWithUserResponseDto> toPostWithUserDtoList(List<Post> posts);

    @Named("toUserDto")
    UserResponseDto toUserDto(User user);
}
