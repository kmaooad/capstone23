package edu.kmaooad.capstone23.jobs.dal;

import org.bson.types.ObjectId;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "jobs")
public class Job {
    public ObjectId id;
    public String name;
    public  String description;
    public boolean isActive;
}

