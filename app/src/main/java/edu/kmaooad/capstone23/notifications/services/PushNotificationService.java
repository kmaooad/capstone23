package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.PushNotification;
import edu.kmaooad.capstone23.notifications.dal.PushNotificationRepository;
import jakarta.inject.Inject;

interface NotificationService {
    PushNotification insert(PushNotification notification);
    PushNotification findNotificationBy(String userId, String notificationEvent);
}
public class PushNotificationService implements NotificationService {

    @Inject
    PushNotificationRepository notificationsRepository;
    @Override
    public PushNotification insert(PushNotification notification) {
        return notificationsRepository.insert(notification);
    }

    @Override
    public PushNotification findNotificationBy(String userId, String notificationEvent) {
        return notificationsRepository.findNotificationBy(userId, notificationEvent);
    }
}
