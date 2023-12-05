package edu.kmaooad.capstone23.notifications.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class NewNotificationCommand {
    @NotBlank(message = "User ID cannot be empty")
    private final String userId;

    @NotBlank(message = "Event type cannot be empty")
    @Pattern(regexp = "LOGIN_SUCCESS|LOGIN_FAILURE", message = "Invalid event type")
    private final String eventType;

    @NotBlank(message = "Notification method cannot be empty")
    @Pattern(regexp = "EMAIL|SMS|TELEGRAM", message = "Invalid notification method")
    private final String notificationType;

    public NewNotificationCommand(String userId, String eventType, String notificationType) {
        this.userId = userId;
        this.eventType = eventType;
        this.notificationType = notificationType;
    }


    public String getUserId() {
        return userId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getNotificationType() {
        return notificationType;
    }


    public String setUserId() {
        return userId;
    }

    public String setEventType() {
        return eventType;
    }

    public String setNotificationType() {
        return notificationType;
    }

}
