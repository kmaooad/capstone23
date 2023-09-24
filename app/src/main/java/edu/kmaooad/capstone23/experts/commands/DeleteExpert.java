package edu.kmaooad.capstone23.experts.commands;

import org.bson.types.ObjectId;

public class DeleteExpert {

    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

}
