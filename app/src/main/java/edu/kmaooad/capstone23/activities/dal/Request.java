package edu.kmaooad.capstone23.activities.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "requestsToJoinActivity")
public class Request {
    public ObjectId id;
    public String activityId;
    public String userName;
    public String status;
}