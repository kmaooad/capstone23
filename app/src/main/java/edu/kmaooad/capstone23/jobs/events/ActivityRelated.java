package edu.kmaooad.capstone23.jobs.events;

import org.bson.types.ObjectId;

import java.util.ArrayList;

public class ActivityRelated {
    private ArrayList<ObjectId> activitiesId;

    public ActivityRelated(ArrayList<ObjectId> activitiesId) {
        this.activitiesId = activitiesId;
    }

    public ArrayList<ObjectId> getActivitiesId() {
        return activitiesId;
    }
}
