package edu.kmaooad.capstone23.departments.commands;

import jakarta.validation.constraints.NotBlank;

public class DeleteDepartment {
    @NotBlank
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

