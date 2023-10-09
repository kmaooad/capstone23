package edu.kmaooad.capstone23.cvs.events;

import org.bson.types.ObjectId;

public class CVDeleted {
    private ObjectId cvId;

    public ObjectId getCVId(){
        return cvId;
    }

    public CVDeleted(ObjectId cvId) {
        this.cvId = cvId;
    }
}
