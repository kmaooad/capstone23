package edu.kmaooad.capstone23.tag.commands;

import jakarta.validation.constraints.NotNull;

public class DeleteTag {
    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
