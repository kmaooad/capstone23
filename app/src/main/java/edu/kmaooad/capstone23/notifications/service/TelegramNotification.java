package edu.kmaooad.capstone23.notifications.service;


import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TelegramNotification implements Notification {
    @Override
    public void send(String userId, String message) {
        System.out.println("Sending telegram message to " + userId + ": " + message);
    }
}
