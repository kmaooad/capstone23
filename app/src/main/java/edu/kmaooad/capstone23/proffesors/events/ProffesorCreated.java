package edu.kmaooad.capstone23.proffesors.events;

import org.bson.types.ObjectId;

public class ProffesorCreated {



    private ObjectId cvId;

    public ObjectId getId(){
        return cvId;
    }

    public ProffesorCreated(ObjectId cvId) {
        this.cvId = cvId;
    }
}
