package edu.kmaooad.capstone23.notifications.dal;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "notification-setting")
public class NotificationSetting {

    public ObjectId id;
    // notifications should be specific to users
    // public User user;
    public NotificationType notificationType;
    public NotificationEvent notificationEvent;

    public NotificationSetting() {

    }

    public NotificationSetting(NotificationType notificationType,
                               NotificationEvent notificationEvent) {
        this.notificationType = notificationType;
        this.notificationEvent = notificationEvent;
    }
}
