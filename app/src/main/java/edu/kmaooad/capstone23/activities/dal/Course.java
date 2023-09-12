package edu.kmaooad.capstone23.activities.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "courses")
public class Course {
    public ObjectId id;
    public String name;
}
