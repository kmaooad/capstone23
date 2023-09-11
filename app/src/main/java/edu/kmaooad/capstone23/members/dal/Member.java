package edu.kmaooad.capstone23.members.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "members")
public class Member {
    public ObjectId id;
    public ObjectId orgId;
    public String firstName;
    public String lastName;
    public String email;
    public boolean isExpert;
    public boolean isAdmin;
}