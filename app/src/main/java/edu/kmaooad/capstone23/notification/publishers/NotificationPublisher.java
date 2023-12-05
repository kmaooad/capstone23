package edu.kmaooad.capstone23.notification.publishers;

import edu.kmaooad.capstone23.notification.dto.Notification;
import edu.kmaooad.capstone23.notification.listeners.NotificationListener;
import jakarta.enterprise.context.RequestScoped;

import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class NotificationPublisher {
  private final List<NotificationListener> listeners;

  public NotificationPublisher() {
    listeners = new ArrayList<>();
  }

  public void subscribe(NotificationListener listener) {
    if (isSubscribed(listener)) {
      return;
    }

    listeners.add(listener);
  }

  public void unsubscribe(NotificationListener listener) {
    listeners.remove(listener);
  }

  public void purge() {
    listeners.clear();
  }

  private boolean isSubscribed(NotificationListener incomingListener) {
    return listeners
        .stream()
        .anyMatch((existingListener) -> (
            existingListener.equals(incomingListener)
        ));
  }

  public void notify(Notification notification) {
    for (NotificationListener listener : listeners) {
      listener.update(notification);
    }
  }
}
