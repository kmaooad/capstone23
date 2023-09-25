package edu.kmaooad.capstone23.groups.events;

import org.bson.types.ObjectId;

public class ActivityAssign {
    private final ObjectId groupId;
    private final ObjectId activityId;

    public ActivityAssign(ObjectId groupId, ObjectId activityId) {
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
