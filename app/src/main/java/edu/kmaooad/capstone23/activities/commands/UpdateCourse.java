package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateCourse {
    @NotNull
    private String id;
    @NotBlank
    @Size(max = 100)
    private String name;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}