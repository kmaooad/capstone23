package edu.kmaooad.capstone23.access_rules.commands;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;

public class DeleteAccessRule {
    @NotNull
    private ObjectId id; 

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }
}
