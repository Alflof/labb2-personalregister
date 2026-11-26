package se.iths.alfred.personalregister.model;

public class Skill {

    private Integer skillId;
    private String skillName;
    private String description;

    public Skill(Integer skillId, String skillName, String description) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.description = description;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public String getDescription() {
        return description;
    }
}
