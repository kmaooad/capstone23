package edu.kmaooad.capstone23.notifications.events;

import org.bson.types.ObjectId;

public class NotificationSent {
    private ObjectId id;

    public NotificationSent(String notificationId) {
        this.id = new ObjectId(notificationId);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
