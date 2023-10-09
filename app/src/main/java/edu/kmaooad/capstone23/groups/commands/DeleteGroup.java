package edu.kmaooad.capstone23.groups.commands;

import jakarta.validation.constraints.NotBlank;

public class DeleteGroup {

    @NotBlank
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
