package edu.kmaooad.capstone23.activities.commands;

import io.smallrye.common.constraint.NotNull;

public class AssignActivityToStudent {
    @NotNull
    private String studentId;
    @NotNull
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public void setStudentId(String groupId) {
        this.studentId = groupId;
    }
}
