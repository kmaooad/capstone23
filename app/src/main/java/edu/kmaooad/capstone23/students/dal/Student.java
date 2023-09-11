package edu.kmaooad.capstone23.students.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import edu.kmaooad.capstone23.activities.Activity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "students")
public class Student {
    public ObjectId objectId;
    public String name;
    public Activity[] activities;
}
