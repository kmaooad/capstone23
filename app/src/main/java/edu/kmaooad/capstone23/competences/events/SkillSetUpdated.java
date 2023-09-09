package edu.kmaooad.capstone23.competences.events;

public class SkillSetUpdated {
    private final String skillSetId;
    private final String skillSetName;

    public SkillSetUpdated(String skillSetId, String name) {
        this.skillSetId = skillSetId;
        this.skillSetName = name;
    }

    public String getSkillSetId() {
        return skillSetId;
    }

    public String getSkillSetName() {
        return skillSetName;
    }
}
