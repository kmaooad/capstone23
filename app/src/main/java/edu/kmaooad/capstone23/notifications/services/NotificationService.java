package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;

import java.util.List;

public interface NotificationService {
    Notification createNotification(Notification notification);

    /**
     * Call method when certain notification event happened
     * @param notificationType type
     */
    void sendNotificationsByType(NotificationType notificationType);
}
