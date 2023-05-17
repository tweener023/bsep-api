package com.bezkoder.spring.security.postgresql.models;

import com.bezkoder.spring.security.postgresql.dtos.UserDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;

    private String projectDescription;

    private Date projectStart;

    private Date projectEnd;

    private Boolean isDeleted;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Project() {
    }

    public Project(String projectName, String projectDescription, Boolean isDeleted, User user, Date projectStart, Date projectEnd) {
        this.projectName = projectName;
        this.projectDescription = projectDescription;
        this.isDeleted = isDeleted;
        this.projectStart = projectStart;
        this.projectEnd = projectEnd;
        this.user = user;
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
    public User getUser() {
        return user;
    }

    public void setUser(User userId) {
        this.user = userId;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
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
