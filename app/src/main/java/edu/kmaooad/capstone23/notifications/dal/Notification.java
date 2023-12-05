package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "notifications")
public class Notification {
    public ObjectId id;
    public ObjectId userId;
    public notificationMethod notificationMethod;
    public eventType eventType;

    public enum notificationMethod {
        EMAIL,
        SMS,
        TELEGRAM
    }

    public enum eventType {
        ADDED_TO_CHAT,
        DELETED_FROM_CHAT,
    }
}
