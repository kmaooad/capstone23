package edu.kmaooad.capstone23.notifications.commands;

import edu.kmaooad.capstone23.notifications.dal.NotificationSendMethod;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AddNotification {
    @NotEmpty
    private String userId;

    @NotNull
    private NotificationType notificationType;
    @NotNull
    private NotificationSendMethod notificationSendMethod;

    @NotEmpty
    private String message;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public NotificationType getType() {
        return notificationType;
    }

    public void setType(NotificationType type) {
        this.notificationType = type;
    }

    public NotificationSendMethod getNotificationSendMethod() {
        return notificationSendMethod;
    }

    public void setNotificationSendMethod(NotificationSendMethod notificationSendMethod) {
        this.notificationSendMethod = notificationSendMethod;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
