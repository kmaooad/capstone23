package edu.kmaooad.capstone23.events.service.types;

public interface NotificationMethodService {
    void sendNotification(String type, String userId, String command);
}
