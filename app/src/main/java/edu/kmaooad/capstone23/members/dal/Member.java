package edu.kmaooad.capstone23.members.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "members")
public class Member {
    public ObjectId id;
    public ObjectId orgId;
    public ObjectId userId;
    public boolean isExpert;
    public boolean isAdmin;
}