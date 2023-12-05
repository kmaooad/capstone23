package edu.kmaooad.capstone23.notifications.services;

import edu.kmaooad.capstone23.notifications.models.NotificationType;

public interface NotificationService {
  void send(NotificationType type, String userId, String message);
}
