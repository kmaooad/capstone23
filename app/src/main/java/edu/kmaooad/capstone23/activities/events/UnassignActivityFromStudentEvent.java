package edu.kmaooad.capstone23.activities.events;

import org.bson.types.ObjectId;

public class UnassignActivityFromStudentEvent {
        public final ObjectId studentId;
    public final ObjectId activityId;

    public UnassignActivityFromStudentEvent(ObjectId studentId, ObjectId activityId) {
        this.studentId = studentId;
        this.activityId = activityId;
    }
}
