package edu.kmaooad.capstone23.groups.events;

import org.bson.types.ObjectId;

public class ActivityUnassigned {
    private final ObjectId groupId;
    private final ObjectId activityId;

    public ActivityUnassigned(ObjectId groupId, ObjectId activityId) {
        this.groupId = groupId;
        this.activityId = activityId;
    }

    public ObjectId getGroupId() {
        return groupId;
    }

    public ObjectId getActivitiesId() {
        return activityId;
    }
}
