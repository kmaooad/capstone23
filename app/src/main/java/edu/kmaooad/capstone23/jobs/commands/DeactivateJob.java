package edu.kmaooad.capstone23.jobs.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public class DeactivateJob {
    private ObjectId id;

    public DeactivateJob() {
    }

    public DeactivateJob(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

}
