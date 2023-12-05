package edu.kmaooad.capstone23.notifications.service.impl;

import edu.kmaooad.capstone23.events.SystemEvent;
import edu.kmaooad.capstone23.notifications.service.TelegramNotificationService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyTelegramNotificationService implements TelegramNotificationService {
    @Override
    public void sendTelegramNotification(SystemEvent event) {
        throw new RuntimeException("Sending notification via Telegram is not implemented");
    }
}
