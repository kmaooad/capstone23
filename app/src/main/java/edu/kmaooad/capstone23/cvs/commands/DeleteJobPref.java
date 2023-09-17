package edu.kmaooad.capstone23.cvs.commands;

import org.bson.types.ObjectId;

public class DeleteJobPref {


    private ObjectId cvId;

    public ObjectId getCvId() {
        return cvId;
    }

    public void setCvId(ObjectId cvId) {
        this.cvId = cvId;
    }
}
