package edu.kmaooad.capstone23.notifications.service;

import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificationService{
    @Inject
    private NotificationRepository notificationsRepository;

    public Notification insert(Notification notification) {
        return notificationsRepository.insert(notification);
    }

    public Notification findNotificationBy(String userId, String notificationType) {
        return notificationsRepository.findNotificationBy(userId,notificationType);
    }
}
