package edu.kmaooad.capstone23.activities.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "activities")
public class Activity {
    public ObjectId id;
    public String name;
    public boolean completed;

    public Activity() {
        this.name = "activity";
        this.completed = false;
    }

    public Activity(String name) {
        this.name = name;
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getName() {
        return name;
    }
}

