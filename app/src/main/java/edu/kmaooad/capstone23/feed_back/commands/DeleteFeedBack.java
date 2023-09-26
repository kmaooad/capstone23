package edu.kmaooad.capstone23.feed_back.commands;

import org.bson.types.ObjectId;

public class DeleteFeedBack {

    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
