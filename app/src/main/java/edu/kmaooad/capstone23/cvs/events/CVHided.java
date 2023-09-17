package edu.kmaooad.capstone23.cvs.events;

import org.bson.types.ObjectId;

public class CVHided{

    private ObjectId cvId;

    public ObjectId getCVId() {
        return cvId;
    }

    public CVHided(ObjectId cvId) {
        this.cvId = cvId;
    }

}
