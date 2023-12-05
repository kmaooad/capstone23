package edu.kmaooad.capstone23.notifications.services;

import java.util.ArrayList;

import edu.kmaooad.capstone23.notifications.models.NotificationPreference;

public interface NotificationPreferenceService {
  ArrayList<NotificationPreference> getPreferences(String userId);

  void setPreference(String userId, ArrayList<NotificationPreference> preference);
  
}
