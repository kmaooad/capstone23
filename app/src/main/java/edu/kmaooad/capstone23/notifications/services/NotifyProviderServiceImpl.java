package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.dal.NotificationType;

public class NotifyProviderServiceImpl implements NotifyProviderService {

    @Override
    public boolean sendNotification(String userInfo, NotificationType notificationType, String message) {
        if (notificationType == NotificationType.EMAIL) {
            //TODO: send by email
            return true;
        }

        if (notificationType == NotificationType.SMS) {
            //TODO: send by sms
            return true;
        }

        if (notificationType == NotificationType.TELEGRAM) {
            //TODO: send by telegram
            return true;
        }

        throw new Error("Unsupported notification message");
    }
}
