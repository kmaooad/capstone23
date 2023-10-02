package edu.kmaooad.capstone23.groups.commands;

import org.bson.types.ObjectId;

public class AssignGroupToActivity {
    private ObjectId groupId;
    private ObjectId activityId;

    public AssignGroupToActivity() {
    }

    public ObjectId getGroupId() {
        return groupId;
    }

    public void setGroupId(ObjectId groupId) {
        this.groupId = groupId;
    }

    public ObjectId getActivityId() {
        return activityId;
    }

    public void setActivityId(ObjectId activityId) {
        this.activityId = activityId;
    }
}
