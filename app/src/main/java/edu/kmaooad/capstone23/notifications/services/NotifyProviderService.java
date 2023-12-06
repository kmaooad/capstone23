package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.NotificationType;

public interface NotifyProviderService {
    boolean sendNotification(String userInfo, NotificationType notificationType, String message);
}
