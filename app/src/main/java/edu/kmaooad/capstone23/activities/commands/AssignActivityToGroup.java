package edu.kmaooad.capstone23.activities.commands;

import io.smallrye.common.constraint.NotNull;

public class AssignActivityToGroup {
    @NotNull
    private String groupId;
    @NotNull
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
