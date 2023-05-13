package com.bezkoder.spring.security.postgresql.dtos;

import com.bezkoder.spring.security.postgresql.models.Skill;

public class SkillDTO {
    private Long skillId;
    private String skillName;
    private String skillLevel;

    public SkillDTO(){

    }
    public SkillDTO(Skill skill){
        skillId = skill.getSkillId();
        skillName = skill.getSkillName();
        skillLevel = skill.getSkillLevel();
    }
    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
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
}
