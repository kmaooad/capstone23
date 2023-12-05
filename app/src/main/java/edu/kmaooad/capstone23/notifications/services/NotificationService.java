package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;

public interface NotificationService {
    Notification insert(Notification notification);
    Notification findNotificationByIdAndType(String userId, String notificationType);
}
