package edu.kmaooad.capstone23.group_templates.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateGroupTemplate {

    @NotBlank
    @Size(min = 3, max = 50)
    private String groupTemplateName;

    public String getGroupTemplateName() {
        return groupTemplateName;
    }

    public void setGroupTemplateName(String groupTemplateName) {
        this.groupTemplateName = groupTemplateName;
    }
}
