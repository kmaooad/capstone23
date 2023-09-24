package edu.kmaooad.capstone23.cvs.events;

import org.bson.types.ObjectId;

public class JobPrefCreated {

    private ObjectId cvId;

    public ObjectId getCVId() {
        return cvId;
    }

    public JobPrefCreated(ObjectId cvId) {
        this.cvId = cvId;
    }

}
