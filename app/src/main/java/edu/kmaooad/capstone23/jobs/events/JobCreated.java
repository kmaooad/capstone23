package edu.kmaooad.capstone23.jobs.events;

import org.bson.types.ObjectId;

public class JobCreated {
    private ObjectId jobId;
    public ObjectId getJobId() {
        return jobId;
    }

    public JobCreated(ObjectId jobId) {
        this.jobId = jobId;
    }
}
