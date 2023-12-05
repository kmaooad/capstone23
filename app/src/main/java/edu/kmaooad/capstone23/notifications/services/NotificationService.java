package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.Notification;

public interface NotificationService {
    Notification save(Notification notification);
    Notification findByUserIdAndType(String userId, String type);
}
