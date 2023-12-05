package edu.kmaooad.capstone23.notifications.services;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.notifications.models.NotificationType;

public interface NotificationService {
  void send(NotificationType type, ObjectId userId, String message);
}
