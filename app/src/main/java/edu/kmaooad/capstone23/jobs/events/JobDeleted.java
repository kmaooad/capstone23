package edu.kmaooad.capstone23.jobs.events;

import org.bson.types.ObjectId;

public class JobDeleted {
    private ObjectId jobId;
    public ObjectId getJobId() {
        return jobId;
    }

    public JobDeleted(ObjectId jobId) {
        this.jobId = jobId;
    }
}
