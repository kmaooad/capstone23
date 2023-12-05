package edu.kmaooad.capstone23.notifications.commands;

import edu.kmaooad.capstone23.notifications.dal.NotificationEvent;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;

public class SendNotification {
    private String userId;
    private NotificationType notificationType;
    private NotificationEvent notificationEvent;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public NotificationEvent getNotificationEvent() {
        return notificationEvent;
    }

    public void setNotificationEvent(NotificationEvent notificationEvent) {
        this.notificationEvent = notificationEvent;
    }
}
