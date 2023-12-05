package edu.kmaooad.capstone23.notifications.service;

import edu.kmaooad.capstone23.jobs.dal.JobRepository;
import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationMethod;
import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService{
    @Inject
    private NotificationRepository notificationsRepository;
    @Override
    public Notification insert(Notification notification) {
        return notificationsRepository.insert(notification);
    }

    @Override
    public void sendNotifications(Notification notification, String message) {
        switch(notification.notificationMethod){
            case EMAIL -> {
                // email
                break;
            }
            case SMS -> {
                //sms
                break;
            }
            case TELEGRAM -> {
                //telegram
                break;
            }
        }
    }
}
