package edu.kmaooad.capstone23.competences.events;

public class SkillSetCreated {
    private String skillSetId;

    public String getSkillSetId() {
        return skillSetId;
    }

    public SkillSetCreated(String skillSetId) {
        this.skillSetId = skillSetId;
    }
}
