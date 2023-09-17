package edu.kmaooad.capstone23.cvs.events;

import org.bson.types.ObjectId;

public class CVShowed{

    private ObjectId cvId;

    public ObjectId getCVId() {
        return cvId;
    }

    public CVShowed(ObjectId cvId) {
        this.cvId = cvId;
    }

}
