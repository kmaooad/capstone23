package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import edu.kmaooad.capstone23.notifications.dal.NotificationType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {
    @Inject
    NotificationRepository notificationRepository;
    @Inject
    NotifyProviderService notifyProviderService;

    @Override
    public Notification insert(Notification notification) {
        return notificationRepository.insert(notification);
    }

    @Override
    public boolean notify(String userInfo, NotificationType type, String message) {
        return notifyProviderService.sendNotification(userInfo, type, message);
    }
}
