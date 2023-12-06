package edu.kmaooad.capstone23.notifications.services;

public interface NotificationTypeService {
    void sendNotification(String type, String userId, String command);
}