package edu.kmaooad.capstone23.activities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "activities")
public class Activity {
    public ObjectId objectId;
    private String name;
    private boolean completed;

    public Activity(String name) {
        this.name = name;
        this.completed = false;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    public void markAsPending() {
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getName() {
        return name;
    }

}

