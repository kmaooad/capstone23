package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

public class ActivateJob {
    private ObjectId id;

    public ActivateJob() {
    }

    public ActivateJob(ObjectId id) {
        this.id = id;
    }

    public ObjectId getJobId() {
        return id;
    }

    public void setJobId(ObjectId id) {
        this.id = id;
    }

}
