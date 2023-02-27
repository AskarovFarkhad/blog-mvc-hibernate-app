package com.blog.mapper;

import com.blog.dto.UserRequestDto;
import com.blog.dto.UserResponseDto;
import com.blog.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {User.class, UserResponseDto.class, UserRequestDto.class},
        imports = UUID.class)
public interface UserMapper {

    @Mapping(target = "externalId", expression = "java(UUID.randomUUID())")
    User toUser(UserRequestDto userRequestDto);

    User toUser(UserResponseDto userResponseDto);

    UserResponseDto toUserResponseDto(User user);
}