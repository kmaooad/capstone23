package edu.kmaooad.capstone23.notifications.handler.impl;

import edu.kmaooad.capstone23.events.SystemEvent;
import edu.kmaooad.capstone23.notifications.service.EmailNotificationService;
import edu.kmaooad.capstone23.notifications.handler.NotificationHandler;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EmailNotificationHandler implements NotificationHandler {
    @Inject
    EmailNotificationService emailNotificationService;

    @Override
    public void onEvent(SystemEvent event) {
        emailNotificationService.sendEmailNotification(event);
    }
}
