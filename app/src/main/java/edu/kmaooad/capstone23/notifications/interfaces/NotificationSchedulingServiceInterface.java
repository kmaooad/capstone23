package edu.kmaooad.capstone23.notifications.interfaces;

import edu.kmaooad.capstone23.mail.service.Notification;

public interface NotificationSchedulingServiceInterface {
    Notification schedule(Notification notification, java.util.Date date);

    Notification findNotificationBy(String userId, String notificationType);
}
