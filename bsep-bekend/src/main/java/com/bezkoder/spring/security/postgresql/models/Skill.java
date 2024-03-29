package com.bezkoder.spring.security.postgresql.models;

import com.bezkoder.spring.security.postgresql.dtos.UserDTO;

import javax.persistence.*;

@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;

    private String skillLevel;

    private Boolean isDeleted;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Skill() {
    }

    public Skill(String skillName, String skillLevel, Boolean isDeleted, User user) {
        this.skillName = skillName;
        this.skillLevel = skillLevel;
        this.isDeleted = isDeleted;
        this.user = user;
    }

    public Long getSkillId() {
        return id;
    }

    public void setSkillId(Long skillId) {
        this.id = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
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



}
