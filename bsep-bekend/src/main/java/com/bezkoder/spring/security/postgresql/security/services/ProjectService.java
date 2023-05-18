package com.bezkoder.spring.security.postgresql.security.services;

import com.bezkoder.spring.security.postgresql.models.Project;
import com.bezkoder.spring.security.postgresql.models.Skill;
import com.bezkoder.spring.security.postgresql.repository.ProjectRepository;
import com.bezkoder.spring.security.postgresql.repository.UserSkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project findOne(Long id) {
        return projectRepository.findById(id).orElseGet(null);
    }

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public void remove(Long id) {
         projectRepository.deleteById(id);
    }

}
