package edu.kmaooad.capstone23.cvs.events;

import org.bson.types.ObjectId;

public class CVCreated {

    private ObjectId cvId;

    public ObjectId getCVId(){
        return cvId;
    }

    public CVCreated(ObjectId cvId) {
        this.cvId = cvId;
    }
}