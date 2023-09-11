package edu.kmaooad.capstone23.orgs.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "jobs")
public class Job {

    public ObjectId id;
    public String description;
    public ObjectId orgId;
    public boolean isActive;
}
