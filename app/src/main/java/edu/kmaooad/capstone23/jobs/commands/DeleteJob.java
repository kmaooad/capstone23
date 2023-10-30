package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

public class DeleteJob {
    private ObjectId jobId;

    public DeleteJob(){};

    public ObjectId getJobId() {
        return jobId;
    }

    public void setJobId(ObjectId jobId) {
        this.jobId = jobId;
    }
}
