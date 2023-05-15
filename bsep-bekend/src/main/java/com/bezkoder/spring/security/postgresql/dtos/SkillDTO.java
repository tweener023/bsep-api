package com.bezkoder.spring.security.postgresql.dtos;

import com.bezkoder.spring.security.postgresql.models.Skill;

public class SkillDTO {
    private Long id;
    private String skillName;
    private String skillLevel;
    private UserDTO user;
    private Boolean isDeleted;

    public SkillDTO(){

    }

    public SkillDTO(Skill skill){
        id = skill.getSkillId();
        skillName = skill.getSkillName();
        skillLevel = skill.getSkillLevel();
        isDeleted = skill.getIsDeleted();
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

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
