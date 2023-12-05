package edu.kmaooad.capstone23.notifications.commands;

import edu.kmaooad.capstone23.notifications.dal.EventType;
import edu.kmaooad.capstone23.notifications.dal.NotificationMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;

public class CreateNotification {
    private ObjectId notificationId;
    @NotBlank
    public EventType eventType;
    @NotBlank
    public NotificationMethod notificationMethod;

    public ObjectId getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(ObjectId notificationId) {
        this.notificationId = notificationId;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public NotificationMethod getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(NotificationMethod notificationMethod) {
        this.notificationMethod = notificationMethod;
    }
}
