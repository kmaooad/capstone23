package edu.kmaooad.capstone23.activities.events;

import org.bson.types.ObjectId;

public class AssignActivityToGroupEvent {
    public final ObjectId groupId;
    public final ObjectId activityId;

    public AssignActivityToGroupEvent(ObjectId groupId, ObjectId activityId) {
        this.groupId = groupId;
        this.activityId = activityId;
    }
}
