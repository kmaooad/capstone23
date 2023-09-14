package edu.kmaooad.capstone23.jobs.commands;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class RelateJobToActivities {
    private ObjectId jobId;
    private ArrayList<ObjectId> activitiesId;
    public RelateJobToActivities(){}

    public RelateJobToActivities(ObjectId jobId, ObjectId activityId){
        this.jobId = jobId;
        this.activitiesId = new ArrayList<>();
        this.activitiesId.add(activityId);
    }

    public RelateJobToActivities(ObjectId jobId, ArrayList<ObjectId> activitiesId){
        this.jobId = jobId;
        this.activitiesId = new ArrayList<>();
        this.activitiesId.addAll(activitiesId);
    }

    public ObjectId getJobId() {
        return jobId;
    }

    public void setJobId(ObjectId jobId) {
        this.jobId = jobId;
    }

    public ArrayList<ObjectId> getActivitiesId() {
        return activitiesId;
    }

    public void setActivitiesId(ArrayList<ObjectId> activitiesId) {
        this.activitiesId = activitiesId;
    }

    public void  addActivityId(ObjectId activityId) {
        this.activitiesId.add(activityId);
    }

    public void  addActivitiesId(ArrayList<ObjectId> activitiesId) {
        this.activitiesId.addAll(activitiesId);
    }
}
