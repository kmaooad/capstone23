package edu.kmaooad.capstone23.notifications.services;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class EmailService implements NotificationTypeService {

    @Override
    public void sendNotification(String type, String userId, String command) {
        System.out.println("[Email Service] Sending email for event '" + type + "' to user ID '" + userId + "' with command details: '" + command + "'");
    }
}
