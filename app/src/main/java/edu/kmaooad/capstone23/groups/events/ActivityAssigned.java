package edu.kmaooad.capstone23.groups.events;

import org.bson.types.ObjectId;

public class ActivityAssigned {
    private final ObjectId groupId;
    private final ObjectId activityId;

    public ActivityAssigned(ObjectId groupId, ObjectId activityId) {
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
