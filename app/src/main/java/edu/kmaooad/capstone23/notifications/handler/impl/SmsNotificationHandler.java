package edu.kmaooad.capstone23.notifications.handler.impl;

import edu.kmaooad.capstone23.events.SystemEvent;
import edu.kmaooad.capstone23.notifications.handler.NotificationHandler;
import edu.kmaooad.capstone23.notifications.service.SmsNotificationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SmsNotificationHandler implements NotificationHandler {
    @Inject
    SmsNotificationService smsNotificationService;

    @Override
    public void onEvent(SystemEvent event) {
        smsNotificationService.sendSmsNotification(event);
    }
}
