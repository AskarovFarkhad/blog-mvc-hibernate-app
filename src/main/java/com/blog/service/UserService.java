package com.blog.service;

import com.blog.model.User;
import com.blog.repository.impl.UserCrudRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserCrudRepositoryImpl repository;

    @Autowired
    public UserService(UserCrudRepositoryImpl repository) {
        this.repository = repository;
    }

    public void save(User user) {
        user.setExternalId(UUID.randomUUID());
        repository.save(user);
    }

    public User getById(Long userId) {
        return repository.findById(userId);
    }

    public void update(Long userId, User user) {
        repository.update(userId, user);
    }

    public void delete(Long userId) {
        repository.delete(userId);
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }
}