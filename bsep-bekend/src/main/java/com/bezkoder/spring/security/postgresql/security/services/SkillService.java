package com.bezkoder.spring.security.postgresql.security.services;

import com.bezkoder.spring.security.postgresql.models.Skill;
import com.bezkoder.spring.security.postgresql.repository.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SkillService {

    @Autowired
    private UserSkillRepository userSkillRepository;


    public List<Skill> findAll() {
        return userSkillRepository.findAll();
    }

    public Skill findOne(Long id) {
        return userSkillRepository.findById(id).orElseGet(null);
    }

    public Skill save(Skill skill) {
        return userSkillRepository.save(skill);
    }

    public void remove(Long id) {
        userSkillRepository.deleteById(id);
    }

}
