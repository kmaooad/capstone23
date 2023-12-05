package edu.kmaooad.capstone23.notifications.dal;

import org.bson.types.ObjectId;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "notifications")
public class UserNotification {
    public ObjectId id;
    public ObjectId userId;
    public String notificationType;
    public String notificationMethod;
    public String notificationMethodInfo;
}
