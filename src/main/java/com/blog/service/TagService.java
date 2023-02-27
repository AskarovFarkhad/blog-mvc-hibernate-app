package com.blog.service;

import com.blog.model.Tag;
import com.blog.repository.impl.PostCrudRepositoryImpl;
import com.blog.repository.impl.TagCrudRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TagService {

    private final TagCrudRepositoryImpl repository;

    private final PostCrudRepositoryImpl postCrudRepository;

    @Autowired
    public TagService(TagCrudRepositoryImpl repository, PostCrudRepositoryImpl postCrudRepository) {
        this.repository = repository;
        this.postCrudRepository = postCrudRepository;
    }

    public void save(Tag tag, Long postId) {
        tag.setExternalId(UUID.randomUUID());
        tag.getPosts().add(postCrudRepository.findById(postId));
        repository.save(tag);
    }

    public Tag getById(Long tagId) {
        return repository.findById(tagId);
    }

    public void update(Long tagId, Tag tag) {
        repository.update(tagId, tag);
    }

    public void delete(Long tagId) {
        repository.delete(tagId);
    }

    public List<Tag> getAll() {
        return repository.findAll();
    }
}