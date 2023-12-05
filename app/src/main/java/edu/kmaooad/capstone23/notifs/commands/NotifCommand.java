package edu.kmaooad.capstone23.notifs.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class NotifCommand {

    @NotEmpty
    private String userId;

    @NotEmpty
    @Pattern(regexp = "Subscribed_to_notifications|Unsubscribed_to_notifications")
    private String notificationType;
    @NotEmpty
    @Pattern(regexp = "email|sms|telegram")
    private String notificationMethod;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String type) {
        this.notificationType = type;
    }

    public String getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(String notificationMethod) {
        this.notificationMethod = notificationMethod;
    }

}
