package edu.kmaooad.capstone23.notifications.events;
import org.bson.types.ObjectId;

public class NotificationAdded {
    private ObjectId notificationId;
    public ObjectId getNotificationId() {
        return notificationId;
    }

    public NotificationAdded(ObjectId notificationId) {
        this.notificationId = notificationId;
    }
}
