package edu.kmaooad.capstone23.groups.events;

public class GroupUpdated {
    private final String id;
    private final String name;
    private final String templateId;

    public GroupUpdated(String id, String name, String templateId) {
        this.id = id;
        this.name = name;
        this.templateId = templateId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTemplateId() {
        return templateId;
    }
}
