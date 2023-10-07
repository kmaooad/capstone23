package edu.kmaooad.capstone23.proffesors.events;

import org.bson.types.ObjectId;

public class ActivityAssigned {
    private ObjectId activityId;
    private ObjectId professorId;

    public ObjectId getActivityId() {
        return activityId;
    }

    public void setActivityId(ObjectId activityId) {
        this.activityId = activityId;
    }

    public ObjectId getProfessorId() {
        return professorId;
    }

    public void setProfessorId(ObjectId professorId) {
        this.professorId = professorId;
    }
}
