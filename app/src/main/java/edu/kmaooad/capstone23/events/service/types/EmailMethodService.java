package edu.kmaooad.capstone23.events.service.types;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class EmailMethodService implements NotificationMethodService {

    @Override
    public void sendNotification(String type, String userId, String command) {
        System.out.println("Email notification sent to " + userId + " for " + type + " event with command " + command);
    }
}
