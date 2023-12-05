package edu.kmaooad.capstone23.notifications.impls;

import edu.kmaooad.capstone23.notifications.NotificationChannel;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmailNotificationChannel implements NotificationChannel {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending telegram notification: " + message + " ...");
    }    
}
