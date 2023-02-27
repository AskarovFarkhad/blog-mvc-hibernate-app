package com.blog.service;

import com.blog.dto.UserRequestDto;
import com.blog.dto.UserResponseDto;
import com.blog.mapper.UserMapper;
import com.blog.model.User;
import com.blog.repository.UserCrudRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserCrudRepositoryImpl repository;

    private final UserMapper mapper;

    @Autowired
    public UserService(UserCrudRepositoryImpl repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(UserRequestDto userRequestDto) {
        User user = mapper.toUser(userRequestDto);
        //user.setExternalId(UUID.randomUUID());
        repository.save(user);
    }

    public UserResponseDto getById(Long userId) {
        return mapper.toUserResponseDto(repository.findById(userId));
    }

    public void update(Long userId, UserResponseDto userResponseDto) {
        repository.update(userId, mapper.toUser(userResponseDto));
    }

    public void delete(Long userId) {
        repository.delete(userId);
    }

    public List<UserResponseDto> getAllUsers() {
        return repository.findAll().stream()
                .map(mapper::toUserResponseDto)
                .collect(Collectors.toList());
    }
}