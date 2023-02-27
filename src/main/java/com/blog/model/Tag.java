package com.blog.model;

import javax.persistence.*;
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
}