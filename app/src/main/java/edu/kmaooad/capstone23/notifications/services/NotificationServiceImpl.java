package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.NotificationRepository;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class NotificationServiceImpl implements NotificationService {

    @Inject
    NotificationRepository notificationRepository;
    @Inject
    TelegramService telegramService;
    @Inject
    SMSService smsService;
    @Inject
    EmailService emailService;

    @Override
    public void sendNotification(String type) {
        notificationRepository.findByCommand(type).forEach(notification -> {
            switch (notification.type) {
                case EMAIL:
                    emailService.sendNotification(type, notification.userId, notification.command);
                    break;
                case SMS:
                    smsService.sendNotification(type, notification.userId, notification.command);
                    break;
                case TELEGRAM:
                    telegramService.sendNotification(type, notification.userId, notification.command);
                    break;
            }
        });
    }
}