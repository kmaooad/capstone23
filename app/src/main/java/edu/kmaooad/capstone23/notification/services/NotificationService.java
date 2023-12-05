package edu.kmaooad.capstone23.notification.services;
import edu.kmaooad.capstone23.notification.dal.Notification;

public interface NotificationService {

    Notification insert(Notification notification);
    Notification findNotificationBy(String userId, String notificationType);

}