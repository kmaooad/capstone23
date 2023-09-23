package edu.kmaooad.capstone23.cvs.events;

import org.bson.types.ObjectId;

public class JobPrefUpdated {

    private ObjectId cvId;

    public ObjectId getCVId() {
        return cvId;
    }

    public JobPrefUpdated(ObjectId cvId) {
        this.cvId = cvId;
    }

}
