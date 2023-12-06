package edu.kmaooad.capstone23.notifications.service;

import edu.kmaooad.capstone23.events.SystemEvent;

public interface TelegramNotificationService {
    void sendTelegramNotification(SystemEvent event);
}
