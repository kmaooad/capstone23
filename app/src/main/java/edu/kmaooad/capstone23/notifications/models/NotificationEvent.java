package edu.kmaooad.capstone23.notifications.models;

public class NotificationEvent {
  public final String type;
  public final String userId;
  public final String message;

  public NotificationEvent(
      String type,
      String userId,
      String message
  ) {
    this.type = type;
    this.userId = userId;
    this.message = message;
  }
}
