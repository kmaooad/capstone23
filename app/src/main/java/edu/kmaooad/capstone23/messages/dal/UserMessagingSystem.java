package edu.kmaooad.capstone23.messages.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "userMessagingTypes")
public class UserMessagingSystem {
    public ObjectId id;
    public ObjectId userId;
    public String messageSystemType;
    public String messageSystemInfo;
}
