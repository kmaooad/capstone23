package edu.kmaooad.capstone23.events.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "notification")
public class Notification {
    public ObjectId id;
    public String userId;
    public String command;
    public NotificationMethod method;
}