package edu.kmaooad.capstone23.activities.events;

import org.bson.types.ObjectId;

public class UnassignActivityFromGroupEvent {
    public final ObjectId groupId;
    public final ObjectId activityId;

    public UnassignActivityFromGroupEvent(ObjectId groupId, ObjectId activityId) {
        this.groupId = groupId;
        this.activityId = activityId;
    }
}
