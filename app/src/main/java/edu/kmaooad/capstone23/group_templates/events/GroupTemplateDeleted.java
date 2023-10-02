package edu.kmaooad.capstone23.group_templates.events;

public class GroupTemplateDeleted {

    private final String id;

    public GroupTemplateDeleted(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
