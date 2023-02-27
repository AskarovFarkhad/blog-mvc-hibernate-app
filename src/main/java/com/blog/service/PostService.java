package com.blog.service;

import com.blog.dto.PostRequestDto;
import com.blog.dto.PostResponseDto;
import com.blog.mapper.PostMapper;
import com.blog.model.Post;
import com.blog.repository.PostCrudRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostCrudRepositoryImpl repository;

    private final PostMapper mapper;

    @Autowired
    public PostService(PostCrudRepositoryImpl repository, PostMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void save(PostRequestDto postRequestDto) {
        Post post = mapper.toPost(postRequestDto);
        repository.save(post);
    }

    public PostResponseDto getById(Long postId) {
        return mapper.toPostResponseDto(repository.findById(postId));
    }

    public void update(Long postId, PostResponseDto postResponseDto) {
        repository.update(postId, mapper.toPost(postResponseDto));
    }

    public void delete(Long postId) {
        repository.delete(postId);
    }

    public List<PostResponseDto> getAll() {
        return repository.findAll().stream()
                .map(mapper::toPostResponseDto)
                .collect(Collectors.toList());
    }
}