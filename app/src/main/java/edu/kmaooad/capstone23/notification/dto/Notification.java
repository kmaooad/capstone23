package edu.kmaooad.capstone23.notification.dto;

import edu.kmaooad.capstone23.notification.typedefs.NotificationType;

public class Notification {
  private final NotificationType type;

  private final String content;

  public Notification(String content) {
    this(NotificationType.REGULAR, content);
  }

  public Notification(NotificationType type, String content) {
    this.type = type;
    this.content = content;
  }

  @Override
  public String toString() {
    return "[" + type + "]: " + content;
  }
}
