package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService
{
    @Inject
    NotificationRepository notificationRepository;

    @Override
    public Notification insert(Notification notification) {
        return notificationRepository.insert(notification);
    }

    @Override
    public Notification findById(Notification notification) {
        return notificationRepository.findById(notification.id);
    }
}
