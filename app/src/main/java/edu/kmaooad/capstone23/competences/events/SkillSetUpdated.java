package edu.kmaooad.capstone23.competences.events;

public class SkillSetUpdated {
    private final String id;
    private final String name;

    public SkillSetUpdated(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
