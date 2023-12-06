package edu.kmaooad.capstone23.notifications.sender;

public interface NotificationSender {
    boolean sendNotification(String eventName, Object payload);
}
