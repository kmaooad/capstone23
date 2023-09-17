package edu.kmaooad.capstone23.cvs.events;

import org.bson.types.ObjectId;

public class StatusSeted{

    private ObjectId cvId;

    public ObjectId getCVId() {
        return cvId;
    }

    public StatusSeted(ObjectId cvId) {
        this.cvId = cvId;
    }

}
