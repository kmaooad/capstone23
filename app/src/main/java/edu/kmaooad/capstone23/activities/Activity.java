package edu.kmaooad.capstone23.activities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "activities")
public class Activity {
    public ObjectId objectId;
    public String name;
}