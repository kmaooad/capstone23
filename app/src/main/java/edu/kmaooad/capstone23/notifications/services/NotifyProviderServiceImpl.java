package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.NType;

public class NotifyProviderServiceImpl implements NotifyProviderService {

    @Override
    public boolean sendNotification(String userInfo, NotificationType notificationType, String message) {
        if (notificationType == NotificationType.EMAIL) {

            return true;
        }

        if (notificationType == NotificationType.SMS) {

            return true;
        }

        if (notificationType == NotificationType.TELEGRAM) {

            return true;
        }

        throw new Error("Unsupported notification message");
    }
}