package edu.kmaooad.capstone23.jobs.events;

import org.bson.types.ObjectId;

public class ActivityUnrelated {
    private final ObjectId jobId;
    private final ObjectId activityId;

    public ActivityUnrelated(ObjectId jobId, ObjectId activityId) {
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
