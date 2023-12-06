package edu.kmaooad.capstone23.notifications.service;

import edu.kmaooad.capstone23.events.SystemEvent;

// Defines contract for email notifications and decouples from concrete implementation
// We can use our own implementation or connect some library to use this interface
// Same with other types of notification services
public interface EmailNotificationService {
    void sendEmailNotification(SystemEvent event);
}
