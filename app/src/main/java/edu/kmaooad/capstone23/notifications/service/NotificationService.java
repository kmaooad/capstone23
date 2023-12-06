package edu.kmaooad.capstone23.notifications.service;

import edu.kmaooad.capstone23.notifications.dal.Notification;
import edu.kmaooad.capstone23.notifications.dal.NotificationMethod;

public interface NotificationService {
    Notification insert(Notification notification);
    void sendNotifications(Notification notification, String message);
}
