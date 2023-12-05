package edu.kmaooad.capstone23.notifications.commands;

import jakarta.validation.constraints.NotBlank;

public class SetNotificationType {
    @NotBlank
    private String userId;
    @NotBlank
    private String notificationType;
    @NotBlank
    private String notificationMethod;

    public SetNotificationType() {
    }

    public SetNotificationType(String userId, String notificationType, String notificationMethod) {
        this.userId = userId;
        this.notificationType = notificationType;
        this.notificationMethod = notificationMethod;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getNotificationMethod() {
        return notificationMethod;
    }

    public void setNotificationMethod(String notificationMethod) {
        this.notificationMethod = notificationMethod;
    }
}
