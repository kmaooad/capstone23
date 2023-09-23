package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

public class DeactivateJob {
    private ObjectId id;

    public DeactivateJob() {
    }

    public DeactivateJob(ObjectId id) {
        this.id = id;
    }

    public ObjectId getJobId() {
        return id;
    }

    public void setJobId(ObjectId id) {
        this.id = id;
    }

}
