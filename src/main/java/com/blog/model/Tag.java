package com.blog.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tags_sequence")
    @SequenceGenerator(name = "tags_sequence", allocationSize = 1)
    private Long tagId;

    @Column(name = "external_id", unique = true)
    private UUID externalId;

    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts;

    public Tag() {
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public UUID getExternalId() {
        return externalId;
    }

    public void setExternalId(UUID externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return tagId.equals(tag.tagId) && externalId.equals(tag.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagId, externalId);
    }

    @Override
    public String toString() {
        return "Tag{" +
                "externalId=" + externalId +
                ", name='" + name + '\'' +
                ", posts=" + posts +
                '}';
    }
}