package edu.kmaooad.capstone23.competences.events;

public class SkillSetUpdated {
    private final String skillSetId;
    private final String name;

    public SkillSetUpdated(String skillSetId, String name) {
        this.skillSetId = skillSetId;
        this.name = name;
    }

    public String getSkillSetId() {
        return skillSetId;
    }

    public String getName() {
        return name;
    }
}
