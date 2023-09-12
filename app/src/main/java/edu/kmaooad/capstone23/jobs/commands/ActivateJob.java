package edu.kmaooad.capstone23.jobs.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public class ActivateJob {
    private ObjectId id;

    public ActivateJob() {
    }

    public ActivateJob(ObjectId id) {
        this.id = id;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

}
