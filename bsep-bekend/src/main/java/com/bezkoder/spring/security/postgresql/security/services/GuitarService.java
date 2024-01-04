package com.bezkoder.spring.security.postgresql.security.services;

import com.bezkoder.spring.security.postgresql.models.Guitar;
import com.bezkoder.spring.security.postgresql.repository.GuitarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuitarService {
    @Autowired
    private GuitarRepository guitarRepository;

    public List<Guitar> findAll(){return guitarRepository.findAll();}

    public Guitar findOne(Long id){return guitarRepository.findById(id).orElseGet(null);}

    public Guitar save(Guitar guitar){
        return guitarRepository.save(guitar);
    }

    public void remove(Long id){
        guitarRepository.deleteById(id);
    }

}
