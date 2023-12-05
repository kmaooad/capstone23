package edu.kmaooad.capstone23.notifications.services;

import java.util.Set;

import org.bson.types.ObjectId;

import edu.kmaooad.capstone23.notifications.dal.NotificationSettings;
import edu.kmaooad.capstone23.notifications.models.NotificationPreference;
import edu.kmaooad.capstone23.notifications.models.NotificationType;

public interface NotificationSettingsService {
  NotificationSettings getPreferences(ObjectId userId);

  void setPreference(ObjectId userId, Set<NotificationPreference> preference, Set<NotificationType> types);
}
