package edu.kmaooad.capstone23.notifications.handler.impl;

import edu.kmaooad.capstone23.events.SystemEvent;
import edu.kmaooad.capstone23.notifications.handler.NotificationHandler;
import edu.kmaooad.capstone23.notifications.service.TelegramNotificationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class TelegramNotificationHandler implements NotificationHandler {
    @Inject
    TelegramNotificationService telegramNotificationService;

    @Override
    public void onEvent(SystemEvent event) {
        telegramNotificationService.sendTelegramNotification(event);
    }
}
