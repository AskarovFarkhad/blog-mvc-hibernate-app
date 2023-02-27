package com.blog.service;

import com.blog.model.Post;
import com.blog.repository.impl.PostCrudRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostService {

    private final PostCrudRepositoryImpl repository;

    @Autowired
    public PostService(PostCrudRepositoryImpl repository) {
        this.repository = repository;
    }

    public void save(Post post) {
        post.setExternalId(UUID.randomUUID());
        repository.save(post);
    }

    public Post getById(Long postId) {
        return repository.findById(postId);
    }

    public void update(Long postId, Post post) {
        repository.update(postId, post);
    }

    public void delete(Long postId) {
        repository.delete(postId);
    }

    public List<Post> getAll() {
        return repository.findAll();
    }
}