package edu.kmaooad.capstone23.notifications.services;

import java.util.Set;

import edu.kmaooad.capstone23.notifications.models.NotificationPreference;
import edu.kmaooad.capstone23.notifications.models.NotificationType;

public interface NotificationSettingsService {
  Set<NotificationPreference> getPreferences(String userId);

  void setPreference(String userId, Set<NotificationPreference> preference, Set<NotificationType> types);
}
