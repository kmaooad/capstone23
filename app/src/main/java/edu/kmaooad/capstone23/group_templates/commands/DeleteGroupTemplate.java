package edu.kmaooad.capstone23.group_templates.commands;

import io.smallrye.common.constraint.NotNull;

public class DeleteGroupTemplate {
    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
