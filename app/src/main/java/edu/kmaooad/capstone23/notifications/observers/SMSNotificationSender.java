package edu.kmaooad.capstone23.notifications.observers;

import edu.kmaooad.capstone23.notifications.Notification;
import edu.kmaooad.capstone23.notifications.NotificationMethod;
import edu.kmaooad.capstone23.notifications.NotificationObserver;
import edu.kmaooad.capstone23.notifications.SendImitator;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class SMSNotificationSender implements NotificationObserver {
    @Inject
    SendImitator imitator;

    @Override
    public void update(Notification notification) {
        // Imitation of sending:
        String message = "Notifying via SMS: " + notification.getRepresentation();
        System.out.println(message);
        imitator.sendMessage(NotificationMethod.SMS, message);
    }
}
