package edu.kmaooad.capstone23.notifications.models;

public enum NotificationPreference {
  TG("tg"),
  EMAIL("email"),
  SMS("sms");

  private final String text;

  NotificationPreference(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
