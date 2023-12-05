package edu.kmaooad.capstone23.notification.listeners;


import edu.kmaooad.capstone23.notification.clients.NotificationDeliveryClient;
import edu.kmaooad.capstone23.notification.dto.Notification;
import edu.kmaooad.capstone23.notification.typedefs.NotificationChannel;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

@RequestScoped
public class EmailNotificationSender implements NotificationListener {
  @Inject
  NotificationDeliveryClient client;

  @Override
  public void update(Notification notification) {
    client.send(NotificationChannel.EMAIL, notification);
  }
}
