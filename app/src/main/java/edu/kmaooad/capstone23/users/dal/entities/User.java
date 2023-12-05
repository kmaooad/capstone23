package edu.kmaooad.capstone23.users.dal.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@MongoEntity(collection = "users")
public class User {
    public ObjectId id;
    public String firstName;
    public String lastName;
    @BsonProperty("unique_email")
    public String email;
    public String phoneNumber;
}
