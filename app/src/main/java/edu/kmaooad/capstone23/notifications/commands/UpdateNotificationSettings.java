package edu.kmaooad.capstone23.notifications.commands;

import edu.kmaooad.capstone23.notifications.models.NotificationPreference;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import java.util.Set;

public class UpdateNotificationSettings {
  private String userId;
  private Set<NotificationType> types;
  private Set<NotificationPreference> preferences;

  public Set<NotificationPreference> getPreferences() {
    return preferences;
  }

  public void setPreferences(Set<NotificationPreference> preferences) {
    this.preferences = preferences;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Set<NotificationType> getTypes() {
    return types;
  }

  public void setTypes(Set<NotificationType> types) {
    this.types = types;
  }
}
