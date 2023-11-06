package edu.kmaooad.capstone23.access_rules.commands;

import jakarta.validation.constraints.NotNull;

public class DeleteAccessRule {
    @NotNull
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
