package edu.kmaooad.capstone23.notifications.services.implementations;

import edu.kmaooad.capstone23.notifications.commands.NotificationTrigger;
import edu.kmaooad.capstone23.notifications.dal.NotificationsRepository;
import edu.kmaooad.capstone23.notifications.services.NotifyService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotifyServiceImpl implements NotifyService {
    @Inject
    NotificationsRepository notificationsRepository;

    @Inject
    NotifyByEmailServiceImpl emailService;

    @Inject
    NotifyByTelegramServiceImpl telegramService;

    @Inject
    NotifyByViberServiceImpl viberService;

    @Override
    public void notify(String type, String message) {
        for (var notification : notificationsRepository.getNotificationsByType(type)) {
            NotifyService notifyService;
            switch (notification.notificationMethod) {
                case NotificationTrigger.EMAIL -> notifyService = emailService;
                case NotificationTrigger.TELEGRAM -> notifyService = telegramService;
                case NotificationTrigger.VIBER -> notifyService = viberService;
                default -> {
                    throw new UnsupportedOperationException("Unknown notification method: " + notification.notificationMethod);
                }
            }
            notifyService.notify(notification.notificationType, message);
        }
    }
}
