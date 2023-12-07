package edu.kmaooad.capstone23.notifications.service;


import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailNotification implements Notification {
    @Override
    public void send(String userId, String message) {
        System.out.println("Sending email to " + userId + ": " + message);
    }
}
