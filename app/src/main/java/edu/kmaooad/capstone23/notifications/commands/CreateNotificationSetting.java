package edu.kmaooad.capstone23.notifications.commands;

import jakarta.validation.constraints.NotNull;

public class CreateNotificationSetting {

//    @NotNull
//    private String userId;

    @NotNull
    private String notificationType;

    @NotNull
    private String notificationEvent;

    public CreateNotificationSetting(String notificationType, String notificationEvent) {
        this.notificationType = notificationType;
        this.notificationEvent = notificationEvent;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public String getNotificationEvent() {
        return notificationEvent;
    }
}
