package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "notifications")
public class Notification {
    public ObjectId id;
    public ObjectId userId;
    public String message;
    public NotificationType notificationType;
    public NotificationSendMethod notificationSendMethod;
}