package com.bezkoder.spring.security.postgresql.security.services;


import com.bezkoder.spring.security.postgresql.models.Guitar;
import com.bezkoder.spring.security.postgresql.models.TopImage;
import com.bezkoder.spring.security.postgresql.repository.TopImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopImageServiceImpl implements TopImageService{
    @Autowired
    private TopImageRepository imageRepository;

    @Override
    public TopImage create(TopImage image) {
        return imageRepository.save(image);
    }
    @Override
    public List<TopImage> viewAll() {
        return (List<TopImage>) imageRepository.findAll();
    }
    @Override
    public TopImage viewById(long id) {
        return imageRepository.findById(id).get();
    }
    @Override
    public TopImage findOneByGuitar(Guitar guitar){return imageRepository.findOneByGuitar(guitar);}

}
