package edu.kmaooad.capstone23.users.commands;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

public class DeleteUser {
    @NotBlank
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
