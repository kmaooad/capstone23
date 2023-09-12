package edu.kmaooad.capstone23.jobs.events;

import org.bson.types.ObjectId;

public class JobDeactivated {
    private ObjectId jobId;
    public JobDeactivated(ObjectId jobId) {
        this.jobId = jobId;
    }
    public ObjectId getJobId() {
        return jobId;
    }

}