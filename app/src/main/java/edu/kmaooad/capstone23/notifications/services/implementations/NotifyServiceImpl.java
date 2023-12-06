package edu.kmaooad.capstone23.notifications.services.implementations;

import edu.kmaooad.capstone23.notifications.services.EmailNotifyService;
import edu.kmaooad.capstone23.notifications.services.NotifyService;
import edu.kmaooad.capstone23.notifications.services.SMSnotifyService;
import edu.kmaooad.capstone23.notifications.services.TelegramNotifyService;
import edu.kmaooad.capstone23.notifications.commands.UserNotificationTrigger;
import edu.kmaooad.capstone23.notifications.services.UserNotificationsService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class NotifyServiceImpl implements NotifyService {
    @Inject
    UserNotificationsService userNotificationsService;

    @Inject
    TelegramNotifyService telegramService;

    @Inject
    EmailNotifyService emailService;

    @Inject
    SMSnotifyService smsService;

    @Override
    public void notify(String type, String message) {

            for (var userMethods : userNotificationsService.getUsersByType(type)) {
                switch (userMethods.notificationMethod) {
                    case UserNotificationTrigger.EMAIL -> {
                        emailService.sendMessage(userMethods.notificationMethodInfo, message);
                    }
                    case UserNotificationTrigger.SMS -> {
                        smsService.sendMessage(userMethods.notificationMethodInfo, message);
                    }
                    case UserNotificationTrigger.TELEGRAM -> {
                        telegramService.sendMessage(userMethods.notificationMethodInfo, message);
                    }
                }
            }
        }
    }