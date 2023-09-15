package edu.kmaooad.capstone23.jobs.events;

import org.bson.types.ObjectId;

import java.util.ArrayList;



public class ActivityRelated {

    private final ObjectId jobId;
    private final ArrayList<ObjectId> activitiesId;

    public ActivityRelated(ObjectId jobId, ArrayList<ObjectId> activitiesId) {
        this.jobId = jobId;
        this.activitiesId = activitiesId;
    }

    public ObjectId getJobId() {
        return jobId;
    }

    public ArrayList<ObjectId> getActivitiesId() {
        return activitiesId;
    }
}