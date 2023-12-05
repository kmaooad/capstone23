package edu.kmaooad.capstone23.notifications.services;

import java.sql.Date;

import edu.kmaooad.capstone23.mail.service.Notification;
import edu.kmaooad.capstone23.notifications.interfaces.NotificationSchedulingServiceInterface;

public final class NotificationSchedulingService implements NotificationSchedulingServiceInterface {

    @Override
    public Notification schedule(Notification notification, Date date) {
        throw new UnsupportedOperationException("Unimplemented method 'schedule'");
    }

    @Override
    public Notification findNotificationBy(String userId, String notificationType) {
        throw new UnsupportedOperationException("Unimplemented method 'findNotificationBy'");
    }
}
