package com.spring.demo.mapper;

import com.spring.demo.dto.user.UserRequestDto;
import com.spring.demo.dto.user.UserResponseDto;
import com.spring.demo.dto.user.UserWithPostsResponseDto;
import com.spring.demo.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = PostMapper.class
)
public interface UserMapper {

    UserResponseDto toDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "posts", ignore = true)
    User toEntity(UserRequestDto userRequestDto);

    List<UserResponseDto> toDtoList(List<User> users);

    UserWithPostsResponseDto toUserWithPostsDto(User user);

    List<UserWithPostsResponseDto> toUserWithPostsDtoList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "posts", ignore = true)
    void updateEntity(
            UserRequestDto userRequestDto,
            @MappingTarget User user
    );
}
