package edu.kmaooad.capstone23.notifications.services;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class SMSService implements NotificationTypeService {

    @Override
    public void sendNotification(String type, String userId, String command) {
        System.out.println("[SMS Service] Alert via SMS for event '" + type + "'. User ID: '" + userId + "', Command: '" + command + "'");
    }
}