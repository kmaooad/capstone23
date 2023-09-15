package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class RelateJobToActivities {
    private ObjectId jobId;
    private ObjectId activityId;
    public RelateJobToActivities(){

    }

    public RelateJobToActivities(ObjectId jobId, ObjectId activityId){
        this.jobId = jobId;
        this.activityId =activityId;
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

    public void setActivitiesId(ArrayList<ObjectId> activitiesId) {
        this.activityId = activityId;
    }

 }
