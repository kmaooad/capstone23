package edu.kmaooad.capstone23.jobs.events;

import org.bson.types.ObjectId;

import java.util.ArrayList;



public class ActivityRelated {

    private final ObjectId jobId;
    private final ObjectId activityId;

    public ActivityRelated(ObjectId jobId, ObjectId activityId) {
        this.jobId = jobId;
        this.activityId = activityId;
    }

    public ObjectId getJobId() {
        return jobId;
    }

    public ObjectId getActivitiesId() {
        return activityId;
    }
}