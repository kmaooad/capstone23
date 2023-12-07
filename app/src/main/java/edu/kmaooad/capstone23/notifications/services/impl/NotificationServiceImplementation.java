package edu.kmaooad.capstone23.notifications.services.impl;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationsRepository;
import edu.kmaooad.capstone23.notifications.services.NotificationService;
import jakarta.inject.Inject;

public class NotificationServiceImplementation implements NotificationService {

    @Inject
    private NotificationsRepository notificationsRepository;
    @Override
    public Notification insert(Notification notification) {
        return notificationsRepository.insert(notification);
    }

    @Override
    public Notification findNotificationBy(String userId, String notificationType) {
        return notificationsRepository.findNotificationBy(userId,notificationType);
    }
}
