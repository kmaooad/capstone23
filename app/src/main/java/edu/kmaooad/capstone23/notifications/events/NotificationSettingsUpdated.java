package edu.kmaooad.capstone23.notifications.events;

public class NotificationSettingsUpdated {
  private boolean success;

  public NotificationSettingsUpdated(boolean success) {
    this.success = success;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }
}
