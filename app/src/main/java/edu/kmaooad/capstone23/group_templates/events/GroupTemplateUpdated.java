package edu.kmaooad.capstone23.group_templates.events;

public class GroupTemplateUpdated {

    private final String id;
    private final String name;

    public GroupTemplateUpdated(String id, String name) {
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
