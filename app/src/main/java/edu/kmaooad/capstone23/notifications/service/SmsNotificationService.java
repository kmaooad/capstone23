package edu.kmaooad.capstone23.notifications.service;

import edu.kmaooad.capstone23.events.SystemEvent;

public interface SmsNotificationService {
    void sendSmsNotification(SystemEvent event);
}
