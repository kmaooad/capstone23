package edu.kmaooad.capstone23.group_templates.events;

public class GroupTemplateCreated {
    private final String groupTemplateId;

    public String getGroupTemplateId() {
        return groupTemplateId;
    }

    public GroupTemplateCreated(String groupTemplateId) {
        this.groupTemplateId = groupTemplateId;
    }
}
