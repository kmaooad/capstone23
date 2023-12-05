package edu.kmaooad.capstone23.notifications.observers;

import edu.kmaooad.capstone23.notifications.Notification;
import edu.kmaooad.capstone23.notifications.NotificationObserver;

public class EmailNotificationSender implements NotificationObserver {
    @Override
    public void update(Notification notification) {
        // Imitation of sending:
        System.out.println("Notifying via Email: " + notification.getRepresentation());
    }
}
