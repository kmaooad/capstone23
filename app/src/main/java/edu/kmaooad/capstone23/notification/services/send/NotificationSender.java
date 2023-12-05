package edu.kmaooad.capstone23.notification.services.send;

import edu.kmaooad.capstone23.users.dal.entities.User;

public interface NotificationSender {
    void sendForUser(String eventType, String payload, User user);
}
