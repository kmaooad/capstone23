package edu.kmaooad.capstone23.notification.clients;

import edu.kmaooad.capstone23.notification.dto.Notification;
import edu.kmaooad.capstone23.notification.typedefs.NotificationChannel;
import jakarta.enterprise.context.RequestScoped;

import java.util.HashMap;
import java.util.Map;

@RequestScoped
public class NotificationDeliveryClient {
  private final Map<NotificationChannel, Notification> sentNotifications;

  public NotificationDeliveryClient() {
    sentNotifications = new HashMap<>();
  }

  public Map<NotificationChannel, Notification> getAllSent() {
    return sentNotifications;
  }

  public void send(NotificationChannel channel, Notification notification) {
    sentNotifications.put(channel, notification);
  }

  public void purge() {
    sentNotifications.clear();
  }
}
