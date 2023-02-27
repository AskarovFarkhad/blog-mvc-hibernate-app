package com.blog.dto;

import com.blog.model.Comment;
import com.blog.model.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

public class UserResponseDto {

    private Long userId;

    private UUID externalId;

    @NotEmpty(message = "\"Name\" field must not be empty")
    @Size(min = 2, message = "\"Name\" field should be don't less 2 characters long")
    private String userName;

    @Email(message = "\"Email\" field should be valid")
    @NotBlank(message = "\"Email\" field must not be empty")
    private String email;

    @NotBlank(message = "\"Password\" field must not be empty")
    @Size(min = 8, message = "\"Password\" field should be don't less 8 characters long")
    private String password;

    private Set<Comment> comments;

    private Set<Post> posts;

    public UserResponseDto() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}