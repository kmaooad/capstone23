package edu.kmaooad.capstone23.notifications.events;

import org.bson.types.ObjectId;

public class NSent {
    private ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public NSent(String nId) {
        this.id = new ObjectId(nId);
    }
}