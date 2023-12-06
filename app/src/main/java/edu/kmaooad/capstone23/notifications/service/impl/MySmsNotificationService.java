package edu.kmaooad.capstone23.notifications.service.impl;

import edu.kmaooad.capstone23.events.SystemEvent;
import edu.kmaooad.capstone23.notifications.service.SmsNotificationService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MySmsNotificationService implements SmsNotificationService {
    @Override
    public void sendSmsNotification(SystemEvent event) {
        throw new RuntimeException("Sending notification via SMS is not implemented");
    }
}
