package com.bezkoder.spring.security.postgresql.security.services;


import com.bezkoder.spring.security.postgresql.models.Permissions;
import com.bezkoder.spring.security.postgresql.models.Project;
import com.bezkoder.spring.security.postgresql.models.User;
import com.bezkoder.spring.security.postgresql.repository.PermissionRepository;
import com.bezkoder.spring.security.postgresql.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;


    public List<Permissions> findAll() {
        return permissionRepository.findAll();
    }

    public Permissions findOne(Long id) {
        return permissionRepository.findById(id).orElseGet(null);
    }

    public Permissions save(Permissions project) {
        return permissionRepository.save(project);
    }

    public void remove(Long id) {
        permissionRepository.deleteById(id);
    }

    public Permissions findOneByUser(User user){return permissionRepository.findOneByUser(user);}

}
