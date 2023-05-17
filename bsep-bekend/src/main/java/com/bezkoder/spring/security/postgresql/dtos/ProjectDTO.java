package com.bezkoder.spring.security.postgresql.dtos;

import com.bezkoder.spring.security.postgresql.models.Project;
import com.bezkoder.spring.security.postgresql.models.Skill;
import com.bezkoder.spring.security.postgresql.models.User;

import java.util.Date;

public class ProjectDTO {
    private Long id;
    private String projectName;
    private String projectDescription;
    private UserDTO user;
    private Boolean isDeleted;
    private Date projectStart;
    private Date projectEnd;

    public ProjectDTO(){

    }

    public ProjectDTO(Project project){
        id = project.getProjectId();
        projectName = project.getProjectName();
        projectDescription = project.getProjectDescription();
        isDeleted = project.getIsDeleted();
        projectStart = project.getProjectStart();
        projectEnd = project.getProjectEnd();
    }

    public Long getProjectId() {
        return id;
    }

    public void setProjectId(Long projectId) {
        this.id = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }


    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setIsDeleted(boolean b) {
        this.isDeleted = b;
    }

    public Date getProjectStart() {
        return projectStart;
    }

    public void setProjectStart(Date projectStart) {
        this.projectStart = projectStart;
    }

    public Date getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(Date projectEnd) {
        this.projectEnd = projectEnd;
    }
}
