package com.bezkoder.spring.security.postgresql.security.services;

import com.bezkoder.spring.security.postgresql.models.Blog;
import com.bezkoder.spring.security.postgresql.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Long id, Blog updatedBlog) {
        if (blogRepository.existsById(id)) {
            updatedBlog.setId(id);
            return blogRepository.save(updatedBlog);
        } else {
            throw new IllegalArgumentException("Blog not found with id: " + id);
        }
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
