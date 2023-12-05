package edu.kmaooad.capstone23.experts.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "expertNotifications")
public class ExpertNotification {
    public ObjectId id;
    public ObjectId expertId;
    public NotificationType notificationType;
    public NotificationTriggerType notificationTriggerType;
    public String message;
}
