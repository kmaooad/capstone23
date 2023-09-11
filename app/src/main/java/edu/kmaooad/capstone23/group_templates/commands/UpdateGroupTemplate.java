package edu.kmaooad.capstone23.group_templates.commands;

import io.smallrye.common.constraint.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateGroupTemplate {
    @NotNull
    private String id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String groupTemplateName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupTemplateName() {
        return groupTemplateName;
    }

    public void setGroupTemplateName(String groupTemplateName) {
        this.groupTemplateName = groupTemplateName;
    }
}
