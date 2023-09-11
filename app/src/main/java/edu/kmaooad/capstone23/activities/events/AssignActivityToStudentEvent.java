package edu.kmaooad.capstone23.activities.events;

import org.bson.types.ObjectId;

public class AssignActivityToStudentEvent {
    public final ObjectId studentId;
    public final ObjectId activityId;

    public AssignActivityToStudentEvent(ObjectId studentId, ObjectId activityId) {
        this.studentId = studentId;
        this.activityId = activityId;
    }

    public ObjectId getStudentId() {
        return studentId;
    }

     public ObjectId getActivityId() {
        return activityId;
    }
}
