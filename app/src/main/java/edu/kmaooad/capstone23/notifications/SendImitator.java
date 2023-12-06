package edu.kmaooad.capstone23.notifications;

import jakarta.enterprise.context.RequestScoped;

import java.util.Map;
import java.util.TreeMap;

@RequestScoped
public class SendImitator {
    private Map<NotificationMethod, String> sentMessages = new TreeMap<>();

    public void sendMessage(NotificationMethod method, String message) {
        sentMessages.put(method, message);
    }

    public void clearMessages() {
        sentMessages = new TreeMap<>();
    }

    public Map<NotificationMethod, String> getSentMessages() {
        return sentMessages;
    }
}
