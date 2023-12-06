package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.mail.service.Notification;
import edu.kmaooad.capstone23.notifications.interfaces.NotificationSchedulingServiceInterface;
import edu.kmaooad.capstone23.notifications.repository.NotificationsRepository;
import jakarta.inject.Inject;

public final class NotificationSchedulingService implements NotificationSchedulingServiceInterface {

    @Inject
    private NotificationsRepository notificationsRepository;

    @Override
    public Notification schedule(Notification notification, java.util.Date date) {
        return notificationsRepository.insert(notification);
    }

    @Override
    public Notification findNotificationBy(String userId, String notificationType) {
        return notificationsRepository.findNotificationBy(userId, notificationType);
    }
}
