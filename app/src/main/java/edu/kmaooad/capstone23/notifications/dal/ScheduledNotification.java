package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "ScheduledNotifications")
public class ScheduledNotification {
    public ObjectId id;
    public ObjectId memberId;
    public NotificationType notificationType;
    public NotificationTriggerType notificationTriggerType;
}
