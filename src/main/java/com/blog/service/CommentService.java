package com.blog.service;

import com.blog.model.Comment;
import com.blog.repository.impl.CommentCrudRepositoryImpl;
import com.blog.repository.impl.PostCrudRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private final CommentCrudRepositoryImpl repository;

    private final PostCrudRepositoryImpl postCrudRepository;

    @Autowired
    public CommentService(CommentCrudRepositoryImpl repository, PostCrudRepositoryImpl postCrudRepository) {
        this.repository = repository;
        this.postCrudRepository = postCrudRepository;
    }

    public void save(Comment comment, Long postId) {
        comment.setExternalId(UUID.randomUUID());
        comment.setPost(postCrudRepository.findById(postId));
        repository.save(comment);
    }

    public Comment getById(Long commentId) {
        return repository.findById(commentId);
    }

    public void update(Long commentId, Comment comment) {
        repository.update(commentId, comment);
    }

    public void delete(Long commentId) {
        repository.delete(commentId);
    }

    public List<Comment> getAll() {
        return repository.findAll();
    }
}