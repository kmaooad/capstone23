package edu.kmaooad.capstone23.groups.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateGroup {
    @NotBlank
    @Size(min = 1, max = 50)
    private String groupName;
    @NotBlank
    private String templateId;

    public CreateGroup(String groupName, String templateId) {
        this.groupName = groupName;
        this.templateId = templateId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }
}
