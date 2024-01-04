package com.bezkoder.spring.security.postgresql.security.services;

import com.bezkoder.spring.security.postgresql.models.Guitar;
import com.bezkoder.spring.security.postgresql.models.TopImage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TopImageService {
    public TopImage create(TopImage image);
    public List<TopImage> viewAll();
    public TopImage viewById(long id);
    public TopImage findOneByGuitar(Guitar guitar);
}
