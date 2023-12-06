package edu.kmaooad.capstone23.notifications.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import edu.kmaooad.capstone23.notifications.dal.Notification;
public class AddNotification {
    @NotEmpty
    private String userId;

    @NotEmpty
    private Notification.notificationMethod notificationMethod;
    @NotEmpty
    private Notification.eventType eventType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Notification.notificationMethod getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(Notification.notificationMethod notificationMethod) {
        this.notificationMethod = notificationMethod;
    }

    public Notification.eventType getEventType() {
        return eventType;
    }

    public void setEventType(Notification.eventType eventType) {
        this.eventType = eventType;
    }
}
