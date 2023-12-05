package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;

import java.util.Optional;

public interface NotificationService {
    Notification createNotification(Notification notification);
    Optional<Notification> findNotificationByUserIdAndType(String userId, String notificationType);
}
