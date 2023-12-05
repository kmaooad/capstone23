package edu.kmaooad.capstone23.events.service;

import edu.kmaooad.capstone23.events.dal.NotificationRepository;
import edu.kmaooad.capstone23.events.service.types.EmailMethodService;
import edu.kmaooad.capstone23.events.service.types.SMSMethodService;
import edu.kmaooad.capstone23.events.service.types.TelegramMethodService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class NotificationServiceImpl implements NotificationService {

    @Inject
    NotificationRepository notificationRepository;

    @Inject
    TelegramMethodService telegramMethodService;

    @Inject
    SMSMethodService smsMethodService;

    @Inject
    EmailMethodService emailMethodService;

    @Override
    public void sendNotification(String type) {
        System.out.println("Sending notification of type " + type);

        notificationRepository.getByCommand(type).forEach(notification -> {
            System.out.println("Notification user: " + notification.userId);
            System.out.println("Notification method: " + notification.method);

            switch (notification.method) {
                case EMAIL:
                    emailMethodService.sendNotification(type, notification.userId, notification.command);
                    break;
                case SMS:
                    smsMethodService.sendNotification(type, notification.userId, notification.command);
                    break;
                case TELEGRAM:
                    telegramMethodService.sendNotification(type, notification.userId, notification.command);
                    break;
            }
        });
    }
}
