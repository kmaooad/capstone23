package edu.kmaooad.capstone23.activities.commands;

import org.bson.types.ObjectId;

import io.smallrye.common.constraint.NotNull;

public class AssignActivityToStudent {
    @NotNull
    private ObjectId studentId;
    @NotNull
    private ObjectId activityId;

    public ObjectId getActivityId() {
        return activityId;
    }

    public ObjectId getStudentId() {
        return studentId;
    }

    public void setActivityId(ObjectId activityId) {
        this.activityId = activityId;
    }

    public void setStudentId(ObjectId groupId) {
        this.studentId = groupId;
    }
}
