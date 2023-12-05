package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;

public interface NotificationService {
    Notification insert(Notification notification);
    Notification findNotificationBy(String userId, String notificationType);
}
