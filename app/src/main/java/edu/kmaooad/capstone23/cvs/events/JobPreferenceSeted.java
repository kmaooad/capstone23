package edu.kmaooad.capstone23.cvs.events;

import org.bson.types.ObjectId;

public class JobPreferenceSeted{

    private ObjectId cvId;

    public ObjectId getCVId() {
        return cvId;
    }

    public JobPreferenceSeted(ObjectId cvId) {
        this.cvId = cvId;
    }

}
