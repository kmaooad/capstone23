package edu.kmaooad.capstone23.notifications.commands;

import edu.kmaooad.capstone23.notifications.models.NotificationPreference;
import edu.kmaooad.capstone23.notifications.models.NotificationType;
import java.util.Set;

import org.bson.types.ObjectId;

public class UpdateNotificationSettings {
  private ObjectId userId;
  private Set<NotificationType> types;
  private Set<NotificationPreference> preferences;

  public Set<NotificationPreference> getPreferences() {
    return preferences;
  }

  public void setPreferences(Set<NotificationPreference> preferences) {
    this.preferences = preferences;
  }

  public ObjectId getUserId() {
    return userId;
  }

  public void setUserId(ObjectId userId) {
    this.userId = userId;
  }

  public Set<NotificationType> getTypes() {
    return types;
  }

  public void setTypes(Set<NotificationType> types) {
    this.types = types;
  }
}
