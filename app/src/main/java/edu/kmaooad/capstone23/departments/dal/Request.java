package edu.kmaooad.capstone23.departments.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;
@MongoEntity(collection = "requestsToJoinDepartment")
public class Request {
    public ObjectId id;
    public String departmentId;
    public String userName;
    public RequestStatus status;
}
