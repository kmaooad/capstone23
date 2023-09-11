package edu.kmaooad.capstone23.activities.events;

import org.bson.types.ObjectId;

public class AssignActivityToGroup {
    private final ObjectId groupId;
    private final ObjectId activityId;

    public AssignActivityToGroup(ObjectId groupId, ObjectId activityId) {
        this.groupId = groupId;
        this.activityId = activityId;
    }

    public ObjectId getGroupId() {
        return groupId;
    }

     public ObjectId getActivityId() {
        return activityId;
    }
}
