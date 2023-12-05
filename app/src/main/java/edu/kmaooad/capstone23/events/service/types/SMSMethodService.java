package edu.kmaooad.capstone23.events.service.types;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class SMSMethodService implements NotificationMethodService {

    @Override
    public void sendNotification(String type, String userId, String command) {
        System.out.println("SMS notification sent to " + userId + " with command " + command);
    }
}
