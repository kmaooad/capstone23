package edu.kmaooad.capstone23.notification.event;

import org.bson.types.ObjectId;

public class NotificationCreated {
    private ObjectId notificationId;
    public ObjectId getNotificationId() {
        return notificationId;
    }

    public NotificationCreated(ObjectId notificationId) {
        this.notificationId = notificationId;
    }
}
