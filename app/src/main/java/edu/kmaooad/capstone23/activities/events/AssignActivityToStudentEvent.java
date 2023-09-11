package edu.kmaooad.capstone23.activities.events;

import org.bson.types.ObjectId;

public class AssignActivityToStudent {
    private final ObjectId studentId;
    private final ObjectId activityId;

    public AssignActivityToStudent(ObjectId id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
