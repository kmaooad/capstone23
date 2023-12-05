package edu.kmaooad.capstone23.notifications.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class CreateNotification {

    @NotEmpty
    @Pattern(regexp = "ACTIVITY_CREATED|ACTIVITY_DELETED")
    private String eventType;
    @NotEmpty
    @Pattern(regexp = "EMAIL|SMS|TELEGRAM")
    private String notificationMethod;
    @NotEmpty
    private String userId;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(String notificationMethod) {
        this.notificationMethod = notificationMethod;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
