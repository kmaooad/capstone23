package edu.kmaooad.capstone23.notifications.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SMSNotification implements Notification {
    @Override
    public void send(String userId, String message) {
        System.out.println("Sending SMS to " + userId + ": " + message);
    }
}
