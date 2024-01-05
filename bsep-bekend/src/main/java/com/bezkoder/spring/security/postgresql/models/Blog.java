package com.bezkoder.spring.security.postgresql.models;

import javax.persistence.*;

@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 10000, name = "blog_content") // Adjust length based on your needs
    private String blogContent;

    public Blog(){}

    // Example constructor without ID for simplicity
    public Blog(String title, String content) {
        this.title = title;
        this.blogContent = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return blogContent;
    }

    public void setContent(String content) {
        this.blogContent = content;
    }
}
