package edu.kmaooad.capstone23.notifications.commands;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class PushNotificationCommand {
    @NotEmpty
    private String userId;

    @NotNull
    @Pattern(regexp = "sms|telegram|email")
    private String notificationDestination;

    @NotNull
    @Pattern(regexp = "USER_CREATED|USER_BLOCKED")
    private String notificationEvent;

    public String getNotificationEvent() {
        return notificationEvent;
    }

    public void setNotificationEvent(String notificationEvent) {
        this.notificationEvent = notificationEvent;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNotificationDestination() {
        return notificationDestination;
    }

    public void setNotificationDestination(String notificationDestination) {
        this.notificationDestination = notificationDestination;
    }
}
