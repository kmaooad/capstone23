package edu.kmaooad.capstone23.activities.commands;

import org.bson.types.ObjectId;

import io.smallrye.common.constraint.NotNull;

public class AssignActivityToGroup {
    @NotNull
    private ObjectId groupId;
    @NotNull
    private ObjectId activityId;

    public ObjectId getActivityId() {
        return activityId;
    }

    public ObjectId getGroupId() {
        return groupId;
    }

    public void setActivityId(ObjectId activityId) {
        this.activityId = activityId;
    }

    public void setGroupId(ObjectId groupId) {
        this.groupId = groupId;
    }
}
