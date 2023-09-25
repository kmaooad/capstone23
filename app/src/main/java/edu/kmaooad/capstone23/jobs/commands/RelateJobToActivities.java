package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class RelateJobToActivities {
    private ObjectId jobId;
    private ObjectId activityId;
    public RelateJobToActivities(){

    }

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
