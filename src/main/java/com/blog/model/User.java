package com.blog.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence")
    @SequenceGenerator(name = "users_sequence", allocationSize = 1)
    private Long userId;

    @Column(name = "external_id", unique = true)
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

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Post> posts = new HashSet<>();

    public User() {
    }

    public void addPosts(Post post) {
        post.setAuthor(this);
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public void addComment(Comment comment) {
        comment.setAuthor(this);
        comments.add(comment);
    }

    public void removeComment(Comment comment) {
        comments.remove(comment);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId.equals(user.userId) && externalId.equals(user.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, externalId);
    }

    @Override
    public String toString() {
        return "User{" +
                "externalId=" + externalId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", comments=" + comments +
                ", posts=" + posts +
                '}';
    }
}