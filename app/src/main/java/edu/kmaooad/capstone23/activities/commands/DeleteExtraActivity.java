package edu.kmaooad.capstone23.activities.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class DeleteExtraActivity {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    private ObjectId id;
    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}