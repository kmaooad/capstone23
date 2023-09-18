package edu.kmaooad.capstone23.orgs.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "requestsToJoinDepartment")
public class Request {
    public ObjectId id;
    public String orgId;
    public String userName;
    public String status;
}
