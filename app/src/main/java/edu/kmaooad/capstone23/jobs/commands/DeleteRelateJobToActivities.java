package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

public class DeleteRelateJobToActivities {

    private ObjectId jobId;
    private ObjectId activityId;
    public DeleteRelateJobToActivities(){}

    public ObjectId getJobId() {
        return jobId;
    }

    public void setJobId(ObjectId jobId) {
        this.jobId = jobId;
    }

    public ObjectId getActivityId() {
        return activityId;
    }

    public void setActivityId(ObjectId activityId) {
        this.activityId = activityId;
    }
}