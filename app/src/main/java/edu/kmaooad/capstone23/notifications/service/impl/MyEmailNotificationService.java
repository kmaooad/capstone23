package edu.kmaooad.capstone23.notifications.service.impl;

import edu.kmaooad.capstone23.events.SystemEvent;
import edu.kmaooad.capstone23.notifications.service.EmailNotificationService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MyEmailNotificationService implements EmailNotificationService {
    @Override
    public void sendEmailNotification(SystemEvent event) {
        throw new RuntimeException("Sending notification via email is not implemented");
    }
}
