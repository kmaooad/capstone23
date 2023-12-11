package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "notifications")
public class Notification {
    public NotificationType notificationType;
    public NotificationEvent event;
    public ObjectId id;
    public ObjectId userId;
}