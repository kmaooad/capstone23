package edu.kmaooad.capstone23.competences.commands;

import jakarta.validation.constraints.NotNull;

public class DeleteTopic {
    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
